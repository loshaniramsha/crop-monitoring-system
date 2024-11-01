package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
    void deleteVehicle(String vehicleCode);
    VehicleDTO getSelectedVehicle(String vehicleCode);
    List<VehicleDTO> getAllVehicles();
}
