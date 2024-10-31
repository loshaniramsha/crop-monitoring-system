package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDAO extends JpaRepository<StaffEntity,String> {
}
