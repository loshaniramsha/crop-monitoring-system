

package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.CropDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.CropService;
import com.example.crop_monitoring_system.utills.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {

    @Autowired
    private CropService cropService;
@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Void> saveCrop(
        @RequestPart("cropName") String cropName,
        @RequestPart("scientificName") String scientificName,
        @RequestPart("cropImage") MultipartFile cropImage,
        @RequestPart("category") String category,
        @RequestPart("cropSeason") String season,
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

    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(
            @PathVariable("cropCode") String cropCode,
            @RequestPart("cropName") String cropName,
            @RequestPart("scientificName") String scientificName,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart("category") String category,
            @RequestPart("cropSeason") String cropSeason,
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("logId") String logId
    ) {
        try {
            // Convert the crop image to Base64
            String base64Image = AppUtil.convertImageToBase64(cropImage.getBytes());

            // Create and populate CropDTO
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropName(cropName);
            cropDTO.setScientificName(scientificName);
            cropDTO.setCropImage(base64Image);
            cropDTO.setCategory(category);
            cropDTO.setCropSeason(cropSeason);
            cropDTO.setFieldCode(fieldCode);
            cropDTO.setLogId(logId);

            // Update crop
            cropService.updateCrop(cropCode, cropDTO);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropCode") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CropDTO> getSelectedCrop(@PathVariable("cropCode") String cropCode) {
        try {
            CropDTO cropDTO = cropService.getSelectedCrop(cropCode);
            return new ResponseEntity<>(cropDTO, HttpStatus.OK);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public List<CropDTO> getAllCrop(){
        return cropService.getAllCrops();
    }


}

