package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.EquipmentDAO;
import com.example.crop_monitoring_system.dto.impl.EquipmentDTO;
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
}
