package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.StaffDAO;
import com.example.crop_monitoring_system.dto.impl.StaffDTO;
import com.example.crop_monitoring_system.service.StaffService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {

    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {

    }

    @Override
    public void deleteStaff(String staffId) {

    }

    @Override
    public StaffDTO getSelectedStaff(String staffId) {
        return null;
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        return List.of();
    }
}
