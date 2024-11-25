package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.UserDAO;
import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.entity.impl.UserEntity;
import com.example.crop_monitoring_system.secure.JWTAuthResponse;
import com.example.crop_monitoring_system.secure.SignIn;
import com.example.crop_monitoring_system.service.AuthService;
import com.example.crop_monitoring_system.service.JWTService;
import com.example.crop_monitoring_system.utills.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService {
    private final UserDAO userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        //Save user
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
        //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken( String accessToken) {
        //extract username
        var username = jwtService.extractUsername(accessToken);
        //get user
        var user = userDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //generate new token
        var generatedToken = jwtService.refreshToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }
}
