package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.CropDAO;
import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dao.MonitoringLOgDAO;
import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.entity.impl.CropEntity;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.CropService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private MonitoringLOgDAO monitoringLOgDAO;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity = mapping.toCropEntity(cropDTO);
        cropEntity.setCropCode(generateCropCode());

        // Fetch the MonitoringLogEntity and set it if logId is provided
        if (cropDTO.getLogCode() != null) {
            Optional<MonitoringLogEntity> logEntityOptional = monitoringLOgDAO.findById(cropDTO.getLogCode());
            if (logEntityOptional.isPresent()) {
                cropEntity.setLog(logEntityOptional.get());
            } else {
                log.error("Monitoring Log not found with ID: {}", cropDTO.getLogCode());
                throw new DataPersistException("Monitoring Log not found with ID: " + cropDTO.getLogCode());
            }
        }

        CropEntity savedEntity = cropDAO.save(cropEntity);
        if (savedEntity == null) {
            log.error("Failed to save crop");
            throw new RuntimeException("Failed to save crop");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> updatedCrop = cropDAO.findById(cropCode);
        if (updatedCrop.isPresent()) {
            CropEntity cropEntity = updatedCrop.get();

            cropEntity.setCropName(cropDTO.getCropName());
            cropEntity.setScientificName(cropDTO.getScientificName());
            cropEntity.setCropImage(cropDTO.getCropImage());
            cropEntity.setCategory(cropDTO.getCategory());
            cropEntity.setCropSeason(cropDTO.getCropSeason());

            // Fetch and set the MonitoringLogEntity if logId is provided
            if (cropDTO.getLogCode() != null) {
                Optional<MonitoringLogEntity> logEntityOptional = monitoringLOgDAO.findById(cropDTO.getLogCode());
                if (logEntityOptional.isPresent()) {
                    cropEntity.setLog(logEntityOptional.get());
                } else {
                    log.error("Monitoring Log not found with ID: {}", cropDTO.getLogCode());
                    throw new DataPersistException("Monitoring Log not found with ID: " + cropDTO.getLogCode());
                }
            }

            cropDAO.save(cropEntity);
        } else {
            log.error("Crop not found with code: {}", cropCode);
            throw new DataPersistException("Crop not found with code: " + cropCode);
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        if (cropDAO.existsById(cropCode)){
            cropDAO.deleteById(cropCode);
        }
        else {
            log.error("Crop not found with code: {}", cropCode);
            throw new DataPersistException("Crop not found");
        }
    }

    @Override
    public CropDTO getSelectedCrop(String cropCode) {
        Optional<CropEntity> searchedCrop=cropDAO.findById(cropCode);
        if (searchedCrop.isPresent()){
            return mapping.toCropDTO(searchedCrop.get());
        }
        else {
            log.error("Crop not found with code: {}", cropCode);
            throw new DataPersistException("Crop not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return mapping.toCropDTOList(cropDAO.findAll());
    }

    @Override
    public String generateCropCode() {
        String maxCropCode=cropDAO.generateCropCode();
        if (maxCropCode==null){
            return "C001";
        }
        int newCropCode=Integer.parseInt(maxCropCode.substring(1))+1;
        return "C"+String.format("%03d",newCropCode);
    }
}
