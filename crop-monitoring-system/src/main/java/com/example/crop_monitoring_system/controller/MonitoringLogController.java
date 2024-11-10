package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.MonitoringLogService;
import com.example.crop_monitoring_system.utills.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/monitoringlog")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestPart("logDate") String logDate,
            @RequestPart("logDetails") String logDetails,
            @RequestPart("observedImage")MultipartFile observedImage
            ){
        String base64Image="";
        try {
            byte[] image=observedImage.getBytes();
            base64Image= AppUtil.convertImageToBase64(image);
            String logCode=monitoringLogService.generateMonitoringLogId();
            MonitoringLogDTO monitoringLogDTO=new MonitoringLogDTO();
            monitoringLogDTO.setLogCode(logCode);
            monitoringLogDTO.setLogDate(logDate);
            monitoringLogDTO.setLogDetails(logDetails);
            monitoringLogDTO.setObservedImage(base64Image);
            monitoringLogService.saveMonitoringLog(monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{logCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLog(@PathVariable String logCode, @RequestBody MonitoringLogDTO monitoringLogDTO) {
    try {
        monitoringLogService.updateMonitoringLog(logCode, monitoringLogDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (DataPersistException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable String logCode) {
        try {
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

