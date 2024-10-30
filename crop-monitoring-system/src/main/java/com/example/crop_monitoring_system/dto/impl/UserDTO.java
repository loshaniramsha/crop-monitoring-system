package com.example.crop_monitoring_system.dto.impl;

import com.example.crop_monitoring_system.dto.UserStates;
import com.example.crop_monitoring_system.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStates {

    private String email;
    private String password;
    private Role role;
}
