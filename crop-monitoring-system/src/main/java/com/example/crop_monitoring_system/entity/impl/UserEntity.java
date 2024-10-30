package com.example.crop_monitoring_system.entity.impl;

import com.example.crop_monitoring_system.entity.Role;
import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity {

    @Id
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
