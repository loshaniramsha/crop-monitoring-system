package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.MonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/monitoringlog")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitoringLog(@RequestBody MonitoringLogDTO monitoringLogDTO){
        try {
            monitoringLogService.saveMonitoringLog(monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

