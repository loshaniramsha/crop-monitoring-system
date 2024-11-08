package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface MonitoringLOgDAO extends JpaRepository<MonitoringLogEntity,String> {
    @Query("SELECT l.logCode FROM MonitoringLogEntity l ORDER BY l.logCode DESC LIMIT 1")
    String generateMonitoringLogId();


}
