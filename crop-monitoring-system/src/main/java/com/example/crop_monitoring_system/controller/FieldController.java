package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.FieldDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.FieldService;
import com.example.crop_monitoring_system.utills.AppUtil;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    @Autowired
    private FieldService fieldService;
@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Void> saveField(
        @RequestPart("fieldName") String fieldName,
        @RequestPart("fieldLocation") String fieldLocation,
        @RequestPart("extentSize") String extentSize,
        @RequestPart("fieldImage1") MultipartFile fieldImage1,
        @RequestPart("fieldImage2") MultipartFile fieldImage2,
        @RequestPart("logId") String logId
) {
    try {
        byte[] image1 = fieldImage1.getBytes();
        String base64Image1 = AppUtil.convertImageToBase64(image1);

        byte[] image2 = fieldImage2.getBytes();
        String base64Image2 = AppUtil.convertImageToBase64(image2);

        String fieldCode = fieldService.generateFieldCode();

        // Set the field DTO values
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setFieldCode(fieldCode);
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setLog(logId);

        // Check if fieldLocation is in "x,y" format or if it should be generated
        if (fieldLocation != null && fieldLocation.matches("\\d+,\\d+")) {
            String[] coordinates = fieldLocation.split(",");
            int x = Integer.parseInt(coordinates[0].trim());
            int y = Integer.parseInt(coordinates[1].trim());
            fieldDTO.setFieldLocation(new Point(x, y));
        } else {
            // Generate a random location if fieldLocation is not provided in "x,y" format
            fieldDTO.setFieldLocation(AppUtil.generateFieldLocation());
        }

        fieldDTO.setExtentSize(Double.parseDouble(extentSize));
        fieldDTO.setFieldImage1(base64Image1);
        fieldDTO.setFieldImage2(base64Image2);

        fieldService.saveField(fieldDTO);

        return new ResponseEntity<>(HttpStatus.OK);

    } catch (DataPersistException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
 }
 @PutMapping(value = "/{fieldCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
 public ResponseEntity<Void> updateField(
         @PathVariable("fieldCode") String fieldCode,
         @RequestPart("fieldName") String fieldName,
         @RequestPart(value = "fieldLocation", required = false) String fieldLocation,
         @RequestPart("extentSize") String extentSize,
         @RequestPart(value = "fieldImage1", required = false) MultipartFile fieldImage1,
         @RequestPart(value = "fieldImage2", required = false) MultipartFile fieldImage2,
         @RequestPart("logId") String logId
 ) {
     try {
         FieldDTO fieldDTO = new FieldDTO();
         fieldDTO.setFieldCode(fieldCode);
         fieldDTO.setFieldName(fieldName);
         fieldDTO.setLog(logId);

         // Parse and set the fieldLocation if provided
         if (fieldLocation != null && fieldLocation.matches("\\d+,\\d+")) {
             String[] coordinates = fieldLocation.split(",");
             int x = Integer.parseInt(coordinates[0].trim());
             int y = Integer.parseInt(coordinates[1].trim());
             fieldDTO.setFieldLocation(new Point(x, y));
         } else {
             fieldDTO.setFieldLocation(AppUtil.generateFieldLocation());
         }

         // Parse extent size
         fieldDTO.setExtentSize(Double.parseDouble(extentSize));

         // Convert images to Base64 if provided
         if (fieldImage1 != null && !fieldImage1.isEmpty()) {
             byte[] image1 = fieldImage1.getBytes();
             fieldDTO.setFieldImage1(AppUtil.convertImageToBase64(image1));
         }
         if (fieldImage2 != null && !fieldImage2.isEmpty()) {
             byte[] image2 = fieldImage2.getBytes();
             fieldDTO.setFieldImage2(AppUtil.convertImageToBase64(image2));
         }

         // Call the service to update the field
         fieldService.updateField(fieldCode, fieldDTO);
         return new ResponseEntity<>(HttpStatus.OK);

     } catch (DataPersistException e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     } catch (IOException | NumberFormatException e) {
         e.printStackTrace();
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }

    @DeleteMapping("/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


