package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dao.MonitoringLOgDAO;
import com.example.crop_monitoring_system.dao.StaffDAO;
import com.example.crop_monitoring_system.dto.impl.StaffDTO;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.crop_monitoring_system.entity.impl.StaffEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.StaffService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private MonitoringLOgDAO monitoringLOgDAO;
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {    staffDTO.setStaffId(generateStaffId());
        staffDTO.setStaffId(generateStaffId());
        StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);

        // Set the log entity if logId is provided
        if (staffDTO.getLogCode() != null) {
            Optional<MonitoringLogEntity> logEntityOptional = monitoringLOgDAO.findById(staffDTO.getLogCode());
            if (logEntityOptional.isPresent()) {
                staffEntity.setLog(logEntityOptional.get());
            } else {
                log.error("Monitoring Log not found with ID: " + staffDTO.getLogCode());
                throw new DataPersistException("Monitoring Log not found with ID: " + staffDTO.getLogCode());
            }
        }

        StaffEntity saved = staffDAO.save(staffEntity);
        if (saved == null) {
            throw new DataPersistException("Staff not saved");
        }
    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> optionalStaff = staffDAO.findById(staffId);
        if (optionalStaff.isPresent()) {
            StaffEntity staffEntity = optionalStaff.get();
            staffEntity.setFirstName(staffDTO.getFirstName());
            staffEntity.setLastName(staffDTO.getLastName());
            staffEntity.setDesignation(staffDTO.getDesignation());
            staffEntity.setGender(staffDTO.getGender());
            staffEntity.setJoiningDate(staffDTO.getJoiningDate());
            staffEntity.setBirthDate(staffDTO.getBirthDate());
            staffEntity.setAddressLine1(staffDTO.getAddressLine1());
            staffEntity.setAddressLine2(staffDTO.getAddressLine2());
            staffEntity.setAddressLine3(staffDTO.getAddressLine3());
            staffEntity.setAddressLine4(staffDTO.getAddressLine4());
            staffEntity.setAddressLine5(staffDTO.getAddressLine5());
            staffEntity.setPhoneNumber(staffDTO.getPhoneNumber());
            staffEntity.setEmail(staffDTO.getEmail());
            staffDAO.save(staffEntity);
        } else {
            log.error("Staff not found with ID: " + staffId);
            throw new DataPersistException("Staff not found");
        }
    }

    @Override
    public void deleteStaff(String staffId) {
        if (staffDAO.existsById(staffId)) {
            staffDAO.deleteById(staffId);
        }
        else {
            log.error("Staff not found with ID: " + staffId);
            throw new DataPersistException("Staff not found");
        }
    }

    @Override
    public StaffDTO getSelectedStaff(String staffId) {
        Optional<StaffEntity> searched = staffDAO.findById(staffId);
        if (searched.isPresent()) {
            return mapping.toStaffDTO(searched.get());
        } else {
            log.error("Staff not found with ID: " + staffId);
            throw new DataPersistException("Staff not found");
        }
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

    @Override
    public void addFieldToStaff(String staffId, String fieldId) {
        StaffEntity staffOptional = staffDAO.findById(staffId).orElseThrow(() -> new NotFoundException("Staff not found with ID: " + staffId));
        FieldEntity fieldOptional = fieldDAO.findById(fieldId).orElseThrow(() -> new NotFoundException("Field not found with ID: " + fieldId));
        staffOptional.addField(fieldOptional);
        staffDAO.save(staffOptional);
    }
    @Override
    public void removeFieldFromStaff(String staffId, String fieldId) {
        StaffEntity staffOptional = staffDAO.findById(staffId).orElseThrow(() -> new NotFoundException("Staff not found with ID: " + staffId));
        FieldEntity fieldOptional = fieldDAO.findById(fieldId).orElseThrow(() -> new NotFoundException("Field not found with ID: " + fieldId));
        staffOptional.removeField(fieldOptional);
        staffDAO.save(staffOptional);
    }

}
