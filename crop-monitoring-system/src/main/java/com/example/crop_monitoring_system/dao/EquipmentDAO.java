package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDAO extends JpaRepository<EquipmentEntity,String> {
}
