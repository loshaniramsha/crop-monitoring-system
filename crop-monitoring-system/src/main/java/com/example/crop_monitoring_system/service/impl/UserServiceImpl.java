package com.example.crop_monitoring_system.service.impl;

import com.example.crop_monitoring_system.dao.UserDAO;
import com.example.crop_monitoring_system.dto.impl.UserDTO;
import com.example.crop_monitoring_system.entity.impl.UserEntity;
import com.example.crop_monitoring_system.exception.DataPersistException;
import com.example.crop_monitoring_system.exception.NotFoundException;
import com.example.crop_monitoring_system.service.UserService;
import com.example.crop_monitoring_system.utills.Mapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        UserEntity save=userDAO.save(mapping.toUserEntity(userDTO));
        if (save==null){
            throw new DataPersistException("User not saved");
        }

    }

    @Override public void updateUser(String userId, UserDTO userDTO) {
        Optional<UserEntity> selectUser=userDAO.findById(userId);
        if (selectUser.isPresent()){
            userDAO.save(mapping.toUserEntity(userDTO));
        }
        else {
            throw new NotFoundException("User"+userId+"not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        if (userDAO.existsById(userId)){
            userDAO.deleteById(userId);
        }
        else {
            throw new DataPersistException("User"+userId+"not found");
        }
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        Optional<UserEntity> searched=userDAO.findById(userId);
        if (searched.isPresent()){
            return mapping.toUserDTO(searched.get());
        }
        else {
            throw new DataPersistException("User"+userId+"not found");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return mapping.toUserDTOList(userDAO.findAll());
    }

}
