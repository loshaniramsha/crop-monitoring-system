package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffDAO extends JpaRepository<StaffEntity,String> {
    @Query("SELECT s.staffId FROM StaffEntity s ORDER BY s.staffId DESC LIMIT 1")
    String generateStaffId();
}
