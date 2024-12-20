package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentDAO extends JpaRepository<EquipmentEntity,String> {
    @Query("SELECT MAX(e.equipmentId) FROM EquipmentEntity e")
    String generateEquipmentId();
}
