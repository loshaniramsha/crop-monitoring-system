package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.EquipmentStates;
import com.example.crop_monitoring_system.entity.EquipmentType;
import com.example.crop_monitoring_system.entity.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStates {
   /* private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private States state;
    private String fieldCode;
    private String staffId;*/
       private String equipmentId;
       private String equipmentName;
       private String equipmentType;
       private String state;
       private String fieldCode;
       private String staffId;

       // Getters and Setters



}
