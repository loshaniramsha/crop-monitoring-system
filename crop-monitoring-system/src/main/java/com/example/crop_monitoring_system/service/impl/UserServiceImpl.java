package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.UserDAO;
import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.service.UserService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Mapping mapping;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(UserDTO userDTO) {

    }

    @Override
    public void updateUser(String userId, UserDTO userDTO) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public String generateUserId() {
        return "";
    }
}
