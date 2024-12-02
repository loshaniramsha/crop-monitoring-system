package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.VehicleDTO;
import com.example.crop_monitoring_system.entity.States;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
    try {

        if (vehicleDTO.getStaffId() != null) {
            vehicleDTO.setState(States.NOT_AVAILABLE.toUpperCase());
        }
        // Check if the state is not null and is a valid enum value
        if (vehicleDTO.getState() != null) {
            States state = States.valueOf(vehicleDTO.getState().toUpperCase()); // Convert String to enum
            if (state == States.AVAILABLE || state == States.NOT_AVAILABLE) {
                System.out.println("staff id: " + vehicleDTO.getStaffId());
                vehicleService.saveVehicle(vehicleDTO);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        // Return 400 Bad Request if the state is invalid or null
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (IllegalArgumentException e) {
        // If state conversion to enum fails, return 400 Bad Request
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (DataPersistException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


    @PutMapping(value = "/{vehicleCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateVehicle(
            @PathVariable("vehicleCode") String vehicleCode,
            @RequestBody VehicleDTO vehicleDTO
    ) {
        try {

            if (vehicleDTO.getStaffId() != null) {
                vehicleDTO.setState(States.NOT_AVAILABLE.toUpperCase());
            }
            vehicleDTO.setVehicleCode(vehicleCode);
            vehicleService.updateVehicle(vehicleCode, vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{vehicleCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDTO getSelectedVehicle(@PathVariable("vehicleCode") String vehicleCode){
        return vehicleService.getSelectedVehicle(vehicleCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/generateId")
    public ResponseEntity<String> generateVehicleId() {
    try {
        String nextId = vehicleService.generateVehicleCode();
        return ResponseEntity.ok(nextId);  // Returns plain string
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
