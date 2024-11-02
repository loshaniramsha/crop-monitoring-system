package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.entity.Role;
import com.example.crop_monitoring_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getRole().equals(Role.ADMIN)){
            userService.saveUser(userDTO);
            return ResponseEntity.ok("User saved successfully");
        }else {
            return ResponseEntity.ok("User not saved. Only Admin can save");
        }

    }


}
