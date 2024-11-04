package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.EquipmentDAO;
import com.example.crop_monitoring_system.dto.impl.EquipmentDTO;
import com.example.crop_monitoring_system.entity.impl.EquipmentEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.EquipmentService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(equipmentDTO);
        equipmentEntity.setEquipmentId(generateEquipmentId());
        EquipmentEntity savedEntity = equipmentDAO.save(equipmentEntity);
        if (savedEntity == null) {
            throw new DataPersistException("Failed to save equipment");
        }

    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> updateEntity = equipmentDAO.findById(equipmentId);
        if (updateEntity.isPresent()) {
            EquipmentEntity entityToUpdate = mapping.toEquipmentEntity(equipmentDTO);
            entityToUpdate.setEquipmentId(updateEntity.get().getEquipmentId()); // Ensure the ID is set before saving
            equipmentDAO.save(entityToUpdate);
        } else {
            throw new DataPersistException("Failed to update equipment");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        if (equipmentDAO.existsById(equipmentId)) {
            equipmentDAO.deleteById(equipmentId);
        } else {
            throw new NotFoundException("Equipment not found");
        }
    }


    @Override
    public EquipmentDTO getSelectedEquipment(String equipmentId) {
        return null;
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
       return mapping.toEquipmentDTOList(equipmentDAO.findAll());
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
