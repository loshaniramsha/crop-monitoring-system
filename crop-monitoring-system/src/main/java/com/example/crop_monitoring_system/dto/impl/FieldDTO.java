package com.example.crop_monitoring_system.dto.impl;
import com.example.crop_monitoring_system.dto.FieldStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStates {
    private String fieldCode;
    private String fieldName;
    private Point fieldLocation;
    private Double extentSize;
    private List<CropDTO> cropCode;
    private List<StaffDTO> staffId;
    private List<EquipmentDTO> equipmentId;
    private String fieldImage1;
    private String fieldImage2;
    private String logCode;
}
