package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.entity.Role;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        // Check if the role is either ADMIN, MANAGER, or SCIENTIST
        if (userDTO.getRole() == Role.ADMIN || userDTO.getRole() == Role.MANAGER || userDTO.getRole() == Role.SCIENTIST) {
            userService.saveUser(userDTO);
            return ResponseEntity.ok("User saved successfully");
        } else {
            return ResponseEntity.ok("User not saved. Only Admin, Manager, or Scientist can save");
        }
    }
    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email) {
        try {
            userService.deleteUser(email); // Call the service to delete the user
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 No Content on successful deletion
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if user not found
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 on error
        }
    }


    @GetMapping(value = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable("email") String email) throws NotFoundException {
        return userService.getSelectedUser(email);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }

}
