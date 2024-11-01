package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.MonitoringLOgDAO;
import com.example.crop_monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.crop_monitoring_system.service.MonitoringLogService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLOgDAO monitoringLOgDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {

    }

    @Override
    public void updateMonitoringLog(String logId, MonitoringLogDTO monitoringLogDTO) {

    }

    @Override
    public void deleteMonitoringLog(String logId) {

    }

    @Override
    public MonitoringLogDTO getSelectedMonitoringLog(String logId) {
        return null;
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        return List.of();
    }
}