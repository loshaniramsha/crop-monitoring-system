package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.service.CropService;

import java.util.List;

public class CropServiceImpl implements CropService {
    @Override
    public void saveCrop(CropDTO cropDTO) {

    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {

    }

    @Override
    public void deleteCrop(String cropCode) {

    }

    @Override
    public CropDTO getSelectedCrop(String cropCode) {
        return null;
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return List.of();
    }

    @Override
    public String generateCropCode() {
        return "";
    }
}
