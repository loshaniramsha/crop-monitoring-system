package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO);
    void updateMonitoringLog(String logId, MonitoringLogDTO monitoringLogDTO);
    void deleteMonitoringLog(String logId);
    MonitoringLogDTO getSelectedMonitoringLog(String logId);
    List<MonitoringLogDTO> getAllMonitoringLogs();
}
