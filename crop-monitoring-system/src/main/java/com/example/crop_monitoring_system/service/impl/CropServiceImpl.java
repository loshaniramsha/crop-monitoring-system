package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.CropDAO;
import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.entity.impl.CropEntity;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.CropService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDAO cropDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldDAO fieldDAO;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity=mapping.toCropEntity(cropDTO);
        cropEntity.setCropCode(generateCropCode());
        System.out.println(cropEntity);
        CropEntity savedEntity=cropDAO.save(cropEntity);
        if (savedEntity==null){
            throw new RuntimeException("Failed to save crop");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> updatedCrop=cropDAO.findById(cropCode);
        if (updatedCrop.isPresent()){
            updatedCrop.get().setCropName(cropDTO.getCropName());
            updatedCrop.get().setScientificName(cropDTO.getScientificName());
            updatedCrop.get().setCropImage(cropDTO.getCropImage());
            updatedCrop.get().setCategory(cropDTO.getCategory());
            updatedCrop.get().setCropSeason(cropDTO.getCropSeason());
            cropDAO.save(updatedCrop.get());
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        if (cropDAO.existsById(cropCode)){
            cropDAO.deleteById(cropCode);
        }
        else {
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
            throw new DataPersistException("Crop not found");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return List.of();
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
