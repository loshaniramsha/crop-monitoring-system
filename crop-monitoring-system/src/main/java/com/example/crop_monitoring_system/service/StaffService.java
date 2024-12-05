package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);
    void updateStaff(String staffId, StaffDTO staffDTO);
    void deleteStaff(String staffId);
    StaffDTO getSelectedStaff(String staffId);
    List<StaffDTO> getAllStaffs();
    String generateStaffId();

    void addFieldToStaff(String staffId, String fieldId);
    void removeFieldFromStaff(String staffId, String fieldId);
}
