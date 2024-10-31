package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLOgDAO extends JpaRepository<MonitoringLogEntity,String> {
}
