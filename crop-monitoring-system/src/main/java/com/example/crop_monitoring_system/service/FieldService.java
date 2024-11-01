package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    void updateField(String fieldCode, FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    FieldDTO getSelectedField(String fieldCode);
    List<FieldDTO> getAllFields();
}
