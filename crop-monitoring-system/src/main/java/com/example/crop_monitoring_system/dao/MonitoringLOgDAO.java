package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonitoringLOgDAO extends JpaRepository<MonitoringLogEntity,String> {
    @Query("SELECT l FROM MonitoringLogEntity l ORDER BY l.logCode DESC LIMIT 1")
    String genarateMonitoringLogId();
}
