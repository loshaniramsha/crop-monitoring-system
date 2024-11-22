package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.MonitoringLogStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStates {

    private String logCode;
    private String logDate;
    private String logDetails;
    private String observedImage;
    private List<FieldDTO> fieldCode;
    private List<CropDTO> cropCode;
    private List<StaffDTO> staffId;
}
