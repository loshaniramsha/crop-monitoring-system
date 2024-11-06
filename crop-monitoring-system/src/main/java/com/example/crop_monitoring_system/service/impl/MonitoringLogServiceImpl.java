package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.MonitoringLOgDAO;
import com.example.crop_monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.MonitoringLogService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLOgDAO monitoringLOgDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        MonitoringLogEntity save=monitoringLOgDAO.save(mapping.toMonitoringLogEntity(monitoringLogDTO));
        if (save==null){
            throw new DataPersistException("Monitoring Log not saved");
        }
    }

    @Override
    public void updateMonitoringLog(String logId, MonitoringLogDTO monitoringLogDTO) {
        Optional<MonitoringLogEntity> updateLog=monitoringLOgDAO.findById(logId);
        if (updateLog.isPresent()){
            updateLog.get().setLogDetails(monitoringLogDTO.getLogDetails());
            monitoringLOgDAO.save(updateLog.get());
        }
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

    @Override
    public String generateMonitoringLogId() {
        String maxLogId = monitoringLOgDAO.generateMonitoringLogId();  // Ensure this method returns a String ID
        if (maxLogId == null) {
            return "ML001";
        }
        int id = Integer.parseInt(maxLogId.replace("ML", "")) + 1;
        return String.format("ML%03d", id);
    }
}
