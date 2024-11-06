package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    void updateCrop(String cropCode, CropDTO cropDTO);
    void deleteCrop(String cropCode);
    CropDTO getSelectedCrop(String cropCode);
    List<CropDTO> getAllCrops();
    String generateCropCode();
}
