package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleDAO extends JpaRepository<VehicleEntity,String> {
    @Query("select v.vehicleCode from VehicleEntity v order by v.vehicleCode desc limit 1")
    String generateVehicleCode();
}
