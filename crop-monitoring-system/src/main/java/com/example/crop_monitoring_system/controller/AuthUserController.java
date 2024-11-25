package com.example.crop_monitoring_system.controller;

import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.secure.JWTAuthResponse;
import com.example.crop_monitoring_system.secure.SignIn;
import com.example.crop_monitoring_system.service.UserService;
import com.example.crop_monitoring_system.service.impl.AuthServiceIMPL;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthUserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthServiceIMPL authService;

    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody UserDTO userDTO) {

        try {

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return ResponseEntity.ok(authService.signUp(userDTO));
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authService.signIn(signIn));
    }

    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestParam ("refreshToken") String refreshToken) {
        return null;
    }
}