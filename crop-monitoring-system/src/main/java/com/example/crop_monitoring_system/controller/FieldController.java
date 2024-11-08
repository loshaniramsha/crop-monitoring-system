package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.FieldDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.FieldService;
import com.example.crop_monitoring_system.utills.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2,
            @RequestPart("logId") String logId
    ){
        String base64Image1="";
        String base64Image2="";

        try {
            {
                byte[] image1=fieldImage1.getBytes();
                base64Image1= AppUtil.convertImageToBase64(image1);
                byte[] image2=fieldImage2.getBytes();
                base64Image2= AppUtil.convertImageToBase64(image2);
                String feildCode=fieldService.generateFieldCode();
                FieldDTO fieldDTO=new FieldDTO();
                fieldDTO.setFieldCode(feildCode);
                fieldDTO.setFieldName(fieldName);
                fieldDTO.setLog(logId);
                fieldDTO.setFieldLocation(null);
                fieldDTO.setExtentSize(Double.parseDouble(extentSize));
                fieldDTO.setFieldImage1(base64Image1);
                fieldDTO.setFieldImage2(base64Image2);
                fieldService.saveField(fieldDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
