package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dao.MonitoringLOgDAO;
import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.dto.impl.EquipmentDTO;
import com.example.crop_monitoring_system.dto.impl.FieldDTO;
import com.example.crop_monitoring_system.dto.impl.StaffDTO;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.FieldService;
import com.example.crop_monitoring_system.utills.AppUtil;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private MonitoringLOgDAO monitoringLOgDAO;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        // Check if log ID is valid and retrieve the corresponding MonitoringLogEntity
        Optional<MonitoringLogEntity> logEntityOpt = monitoringLOgDAO.findById(fieldDTO.getLogCode());
        if (logEntityOpt.isEmpty()) {
            throw new DataPersistException("Log ID is invalid or does not exist");
        }

        // Convert FieldDTO to FieldEntity and set the log entity
        FieldEntity fieldEntity = mapping.toFieldEntity(fieldDTO);
        fieldEntity.setLog(logEntityOpt.get());

        // Save the field entity
        FieldEntity savedFieldEntity = fieldDAO.save(fieldEntity);

        // Ensure that the entity was saved successfully
        if (savedFieldEntity == null || savedFieldEntity.getLog() == null) {
            throw new DataPersistException("Field not saved, logId is null");
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        // Retrieve the existing field entity by its fieldCode
        Optional<FieldEntity> existingFieldOpt = fieldDAO.findById(fieldCode);
        if (existingFieldOpt.isEmpty()) {
            throw new DataPersistException("Field with the provided code does not exist");
        }

        // Convert the DTO to the entity and update the entity's details
        FieldEntity existingField = existingFieldOpt.get();
        FieldEntity updatedField = mapping.toFieldEntity(fieldDTO);

        // Update necessary fields
        existingField.setFieldName(updatedField.getFieldName());
        existingField.setFieldLocation(updatedField.getFieldLocation());
        existingField.setExtentSize(updatedField.getExtentSize());
        existingField.setFieldImage1(updatedField.getFieldImage1());
        existingField.setFieldImage2(updatedField.getFieldImage2());
        // Optional: Update the log entity if needed
        if (updatedField.getLog() != null) {
            existingField.setLog(updatedField.getLog());
        }

        // Save the updated field entity
        fieldDAO.save(existingField);
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> fieldEntityOpt = fieldDAO.findById(fieldCode);
        if (fieldEntityOpt.isEmpty()) {
            throw new DataPersistException("Field with the provided code does not exist.");
        }
        fieldDAO.delete(fieldEntityOpt.get());
    }

    @Override
    public FieldDTO getSelectedField(String fieldCode) {
        Optional<FieldEntity> fieldEntityOpt = fieldDAO.findById(fieldCode);
        if (fieldEntityOpt.isEmpty()) {
            throw new DataPersistException("Field with the provided code does not exist.");
        }

        FieldEntity fieldEntity = fieldEntityOpt.get();
        FieldDTO fieldDTO = new FieldDTO();

        // Set basic fields
        fieldDTO.setFieldCode(fieldEntity.getFieldCode());
        fieldDTO.setFieldLocation(fieldEntity.getFieldLocation());
        fieldDTO.setFieldName(fieldEntity.getFieldName());
        fieldDTO.setExtentSize(fieldEntity.getExtentSize());
        fieldDTO.setFieldImage1(fieldEntity.getFieldImage1());
        fieldDTO.setFieldImage2(fieldEntity.getFieldImage2());

        // Map List<CropEntity> to List<CropDTO>
        List<CropDTO> cropDTOs = fieldEntity.getCrops().stream()
                .map(cropEntity -> {
                    CropDTO cropDTO = new CropDTO();
                    cropDTO.setCropCode(cropEntity.getCropCode());
                    cropDTO.setCropName(cropEntity.getCropName());
                    // Set other fields as needed
                    return cropDTO;
                })
                .collect(Collectors.toList());
        fieldDTO.setCrops(cropDTOs);

        // Map List<StaffEntity> to List<StaffDTO>
        List<StaffDTO> staffDTOs = fieldEntity.getStaffs().stream()
                .map(staffEntity -> {
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setStaffId(staffEntity.getStaffId());
                    staffDTO.setFirstName(staffEntity.getStaffId());
                    // Set other fields as needed
                    return staffDTO;
                })
                .collect(Collectors.toList());
        fieldDTO.setStaffs(staffDTOs);

        // Map List<EquipmentEntity> to List<EquipmentDTO>
        List<EquipmentDTO> equipmentDTOs = fieldEntity.getEquipments().stream()
                .map(equipmentEntity -> {
                    EquipmentDTO equipmentDTO = new EquipmentDTO();
                    equipmentDTO.setEquipmentId(equipmentEntity.getEquipmentId());
                    equipmentDTO.setEquipmentName(equipmentEntity.getEquipmentName());
                    // Set other fields as needed
                    return equipmentDTO;
                })
                .collect(Collectors.toList());
        fieldDTO.setEquipments(equipmentDTOs);

        // Check if log is not null before setting it
        if (fieldEntity.getLog() != null) {
            fieldDTO.setLogCode(fieldEntity.getLog().getLogCode()); // or getLogDetails() as needed
        }

        return fieldDTO;
    }

    @Override
    public List<FieldDTO> getAllFields() {

        List<FieldEntity> fieldEntities=fieldDAO.findAll();
        return mapping.toFieldDTOList(fieldEntities);

    }


    @Override
    public String generateFieldCode() {
        String maxFieldId=fieldDAO.generateFieldCode();
        if (maxFieldId==null){
            return "Field-001";
        }
        int newFieldId=Integer.parseInt(maxFieldId.replace("Field-",""))+1;
        return "Field-"+String.format("%03d",newFieldId);
    }
}
