package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.FieldDAO;
import com.example.crop_monitoring_system.dto.impl.FieldDTO;
import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.FieldService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity save=fieldDAO.save(mapping.toFieldEntity(fieldDTO));
        if (save==null){
            throw new DataPersistException("Field not saved");
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String fieldCode) {

    }

    @Override
    public FieldDTO getSelectedField(String fieldCode) {
        return null;
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return List.of();
    }

    @Override
    public String generateFieldCode() {
        String maxFieldId=fieldDAO.generateFieldCode();
        if (maxFieldId==null){
            return "Field-001";
        }
        int newFieldId=Integer.parseInt(maxFieldId.replace("Field-",""))+1;
        return "Field-"+String.format("%03d",newFieldId);
    }
}
