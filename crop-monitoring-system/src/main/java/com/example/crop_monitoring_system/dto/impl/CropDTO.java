package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.CropStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropStates {
    private String cropCode;
    private String cropName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String cropSeason;
    private String fieldCode;
    private String LogId;

}
