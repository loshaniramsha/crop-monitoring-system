package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.EquipmentDAO;
import com.example.crop_monitoring_system.dto.impl.EquipmentDTO;
import com.example.crop_monitoring_system.entity.impl.EquipmentEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.EquipmentService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDAO equipmentDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        // Generate and set the ID for the new equipment
        String generatedId = generateEquipmentId();
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(equipmentDTO);
        equipmentEntity.setEquipmentId(generatedId); // Ensure the ID is set

        EquipmentEntity savedEntity = equipmentDAO.save(equipmentEntity);
        if (savedEntity == null) {
            throw new DataPersistException("Failed to save equipment");
        }

    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {

    }

    @Override
    public void deleteEquipment(String equipmentId) {

    }

    @Override
    public EquipmentDTO getSelectedEquipment(String equipmentId) {
        return null;
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return List.of();
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
