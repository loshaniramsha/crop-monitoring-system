package com.example.crop_monitoring_system.service;


import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.secure.JWTAuthResponse;
import com.example.crop_monitoring_system.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
