

package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.service.CropService;
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

import java.io.IOException;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropName") String cropName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("logId") String logId
    ) {
        String base64Image = "";
        try {
            byte[] image = cropImage.getBytes();
            base64Image = AppUtil.convertImageToBase64(image);

            String cropCode = cropService.generateCropCode();
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(cropCode);
            cropDTO.setCropName(cropName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCropImage(base64Image);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(season);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setLogId(logId);

            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

