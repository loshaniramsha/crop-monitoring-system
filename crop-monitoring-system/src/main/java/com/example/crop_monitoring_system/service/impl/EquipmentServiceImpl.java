package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.EquipmentDAO;
import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dao.StaffDAO;
import com.example.crop_monitoring_system.dto.impl.EquipmentDTO;
import com.example.crop_monitoring_system.entity.EquipmentType;
import com.example.crop_monitoring_system.entity.States;
import com.example.crop_monitoring_system.entity.impl.EquipmentEntity;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.EquipmentService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private StaffDAO staffDAO;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        // Map DTO to Entity
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(equipmentDTO);
        equipmentEntity.setEquipmentId(generateEquipmentId());

        // Validate and Set EquipmentType
        if (equipmentDTO.getEquipmentType() != null) {
            try {
                EquipmentType equipmentType = EquipmentType.valueOf(equipmentDTO.getEquipmentType().toUpperCase());
                equipmentEntity.setEquipmentType(equipmentType);
            } catch (IllegalArgumentException e) {
                log.error("Invalid Equipment Type: {}", equipmentDTO.getEquipmentType());
                throw new DataPersistException("Invalid Equipment Type: " + equipmentDTO.getEquipmentType());
            }
        }

        // Validate and Set FieldEntity
        if (equipmentDTO.getFieldCode() != null && !equipmentDTO.getFieldCode().isEmpty()) {
            Optional<FieldEntity> fieldEntityOptional = fieldDAO.findById(equipmentDTO.getFieldCode());
            if (fieldEntityOptional.isPresent()) {
                equipmentEntity.setField(fieldEntityOptional.get());
            } else {
                log.error("Field not found with ID: {}", equipmentDTO.getFieldCode());
                throw new DataPersistException("Field not found with ID: " + equipmentDTO.getFieldCode());
            }
        }

        // Validate and Set StaffEntity
        if (equipmentDTO.getStaffId() != null && !equipmentDTO.getStaffId().isEmpty()) {
            Optional<StaffEntity> staffEntityOptional = staffDAO.findById(equipmentDTO.getStaffId());
            if (staffEntityOptional.isPresent()) {
                equipmentEntity.setStaff(staffEntityOptional.get());
            } else {
                log.error("Staff not found with ID: {}", equipmentDTO.getStaffId());
                throw new DataPersistException("Staff not found with ID: " + equipmentDTO.getStaffId());
            }
        }

        // Save Equipment Entity
        EquipmentEntity savedEntity = equipmentDAO.save(equipmentEntity);
        if (savedEntity == null) {
            log.error("Failed to save equipment");
            throw new DataPersistException("Failed to save equipment");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> updateEntityOptional = equipmentDAO.findById(equipmentId);

        if (updateEntityOptional.isPresent()) {
            EquipmentEntity existingEntity = updateEntityOptional.get();

            // Update fields as needed, ensuring we don't overwrite the equipmentId
            if (equipmentDTO.getEquipmentName() != null) {
                existingEntity.setEquipmentName(equipmentDTO.getEquipmentName());
            }

            if (equipmentDTO.getEquipmentType() != null) {
                try {
                    EquipmentType equipmentType = EquipmentType.valueOf(equipmentDTO.getEquipmentType().toUpperCase());
                    existingEntity.setEquipmentType(equipmentType);
                } catch (IllegalArgumentException e) {
                    log.error("Invalid Equipment Type: {}", equipmentDTO.getEquipmentType());
                    throw new DataPersistException("Invalid Equipment Type: " + equipmentDTO.getEquipmentType());
                }
            }

            if (equipmentDTO.getState() != null) {
                try {
                    // Ensure that the state is converted to uppercase before passing to valueOf
                    existingEntity.setState(States.valueOf(equipmentDTO.getState().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    log.error("Invalid State: {}", equipmentDTO.getState());
                    throw new DataPersistException("Invalid State: " + equipmentDTO.getState());
                }
            }

            // Fetch and set the FieldEntity if fieldId is provided
            if (equipmentDTO.getFieldCode() != null) {
                Optional<FieldEntity> fieldEntityOptional = fieldDAO.findById(equipmentDTO.getFieldCode());
                if (fieldEntityOptional.isPresent()) {
                    existingEntity.setField(fieldEntityOptional.get());
                } else {
                    log.error("Field not found with ID: {}", equipmentDTO.getFieldCode());
                    throw new DataPersistException("Field not found with ID: " + equipmentDTO.getFieldCode());
                }
            }

            // Fetch and set the StaffEntity if staffId is provided
            if (equipmentDTO.getStaffId() != null) {
                Optional<StaffEntity> staffEntityOptional = staffDAO.findById(equipmentDTO.getStaffId());
                if (staffEntityOptional.isPresent()) {
                    existingEntity.setStaff(staffEntityOptional.get());
                } else {
                    log.error("Staff not found with ID: {}", equipmentDTO.getStaffId());
                    throw new DataPersistException("Staff not found with ID: " + equipmentDTO.getStaffId());
                }
            }

            // Save the updated entity
            EquipmentEntity updatedEntity = equipmentDAO.save(existingEntity);
            if (updatedEntity == null) {
                throw new DataPersistException("Failed to update equipment");
            }

        } else {
            log.error("Equipment not found with ID: {}", equipmentId);
            throw new DataPersistException("Equipment not found with ID: " + equipmentId);
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        if (equipmentDAO.existsById(equipmentId)) {
            equipmentDAO.deleteById(equipmentId);
        } else {
            log.error("Equipment not found with ID: {}", equipmentId);
            throw new NotFoundException("Equipment not found");
        }
    }

    @Override
    public EquipmentDTO getSelectedEquipment(String equipmentId) {
        Optional<EquipmentEntity> searched = equipmentDAO.findById(equipmentId);
        if (searched.isPresent()){
            return mapping.toEquipmentDTO(searched.get());
        }
        else {
            log.error("Equipment not found with ID: {}", equipmentId);
            throw new NotFoundException("Equipment not found");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<EquipmentEntity> equipmentEntities = equipmentDAO.findAll();
        return mapping.toEquipmentDTOList(equipmentEntities);
    }

    @Override
    public String generateEquipmentId() {
        String maxEquipmentId = equipmentDAO.generateEquipmentId();
        if (maxEquipmentId == null) {
            return "E00-001";
        }
        int id = Integer.parseInt(maxEquipmentId.replace("E00-", "")) + 1;
        return String.format("E00-%03d", id);
    }
}
