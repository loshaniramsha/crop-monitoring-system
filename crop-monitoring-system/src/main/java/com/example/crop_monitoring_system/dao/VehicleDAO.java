package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDAO extends JpaRepository<VehicleEntity,String> {
}
