package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);

}
