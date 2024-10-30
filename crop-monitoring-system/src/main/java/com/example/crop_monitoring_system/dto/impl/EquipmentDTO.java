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
    private String equipmentId;
    private String equipmentName;
    private Enum<EquipmentType> equipmentType;
    private Enum<States> state;
    private List<FieldDTO> field;
    private List<StaffDTO> staff;

}
