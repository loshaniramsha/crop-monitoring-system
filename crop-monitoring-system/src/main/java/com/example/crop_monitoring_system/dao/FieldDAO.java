package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDAO extends JpaRepository<FieldEntity,String> {
}
