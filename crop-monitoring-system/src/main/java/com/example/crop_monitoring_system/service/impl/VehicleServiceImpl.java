package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.VehicleDAO;
import com.example.crop_monitoring_system.dto.impl.VehicleDTO;
import com.example.crop_monitoring_system.entity.impl.VehicleEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicleCode(generateVehicleCode());
        VehicleEntity save=vehicleDAO.save(mapping.toVehicleEntity(vehicleDTO));
        if (save==null){
            log.error("Vehicle not saved");
            throw new DataPersistException("Vehicle not saved");
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> selectVehicle=vehicleDAO.findById(vehicleCode);
        if (selectVehicle.isPresent()){
            vehicleDAO.save(mapping.toVehicleEntity(vehicleDTO));
        }
        else {
            log.error("Vehicle not found");
            new DataPersistException("Vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        if (vehicleDAO.existsById(vehicleCode)){
            vehicleDAO.deleteById(vehicleCode);
        }
        else {
            log.error("Vehicle not found");
            throw new DataPersistException("Vehicle not found");
        }
    }

    @Override
    public VehicleDTO getSelectedVehicle(String vehicleCode) {
        Optional<VehicleEntity> searched=vehicleDAO.findById(vehicleCode);
        if (searched.isPresent()){
            return mapping.toVehicleDTO(searched.get());
        }
        else {
            log.error("Vehicle not found");
            throw new DataPersistException("Vehicle not found");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.toVehicleDTOList(vehicleDAO.findAll());
    }

    @Override
    public String generateVehicleCode() {
        String MaxVehicleId=vehicleDAO.generateVehicleCode();
        if (MaxVehicleId==null){
            return "V00-001";

        }
        int newVehicleId=Integer.parseInt(MaxVehicleId.replace("V00-",""))+1;
        return "V00-"+String.format("%03d",newVehicleId);

    }
}
