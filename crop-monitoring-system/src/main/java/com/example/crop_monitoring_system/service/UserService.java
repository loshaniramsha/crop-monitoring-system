package com.example.crop_monitoring_system.service;

import com.example.crop_monitoring_system.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);
    UserDTO getSelectedUser(String userId);
    List<UserDTO> getAllUsers();

    UserDetailsService userDetailsService();

}
