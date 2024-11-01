package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.VehicleDAO;
import com.example.crop_monitoring_system.dto.impl.VehicleDTO;
import com.example.crop_monitoring_system.service.VehicleService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {

    }

    @Override
    public void deleteVehicle(String vehicleCode) {

    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleCode) {
        return null;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return List.of();
    }
}
