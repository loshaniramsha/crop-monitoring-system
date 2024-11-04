package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.StaffDAO;
import com.example.crop_monitoring_system.dto.impl.StaffDTO;
import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
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
        staffDTO.setStaffId(generateStaffId());
        StaffEntity save=staffDAO.save(mapping.toStaffEntity(staffDTO));
        if (save==null){
            throw new DataPersistException("Staff not saved");
        }
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
        return mapping.toStaffDTOList(staffDAO.findAll());
    }

    @Override
    public String generateStaffId() {
        String staffId=staffDAO.generateStaffId();
        if (staffId==null){
            return "ST001";
        }
      int newStaffId=Integer.parseInt(staffId.replace("ST",""))+1;
      return String.format("ST%03d",newStaffId);
    }
}
