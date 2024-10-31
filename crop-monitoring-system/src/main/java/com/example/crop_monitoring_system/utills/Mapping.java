package com.example.crop_monitoring_system.utills;

import com.example.crop_monitoring_system.dto.impl.*;
import com.example.crop_monitoring_system.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Crop
    public CropDTO toCropDTO(CropEntity cropEntity){return modelMapper.map(cropEntity, CropDTO.class);}
    public CropEntity toCropEntity(CropDTO cropDTO){return modelMapper.map(cropDTO, CropEntity.class);}
    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntities){return cropEntities.stream().map(this::toCropDTO).toList();}


    //Equipment
    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity){return modelMapper.map(equipmentEntity, EquipmentDTO.class);}
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO){return modelMapper.map(equipmentDTO, EquipmentEntity.class);}
    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity>equipmentEntities){return equipmentEntities.stream().map(this::toEquipmentDTO).toList();}

    //Field
    public FieldDTO toFieldDTO(FieldEntity fieldEntity){return modelMapper.map(fieldEntity, FieldDTO.class);}
    public FieldEntity toFieldEntity(FieldDTO fieldDTO){return modelMapper.map(fieldDTO, FieldEntity.class);}
    public List<FieldDTO> toFieldDTOList(List<FieldEntity> fieldEntities){return fieldEntities.stream().map(this::toFieldDTO).toList();}

    //MonitoringLog
    public MonitoringLogDTO toMonitoringLogDTO(MonitoringLogEntity monitoringLogEntity){return modelMapper.map(monitoringLogEntity, MonitoringLogDTO.class);}
    public MonitoringLogEntity toMonitoringLogEntity(MonitoringLogDTO monitoringLogDTO){return modelMapper.map(monitoringLogDTO, MonitoringLogEntity.class);}
    public List<MonitoringLogDTO> toMonitoringLogDTOList(List<MonitoringLogEntity> monitoringLogEntities){return monitoringLogEntities.stream().map(this::toMonitoringLogDTO).toList();}

    //Staff
    public StaffDTO toStaffDTO(StaffEntity staffEntity){return modelMapper.map(staffEntity, StaffDTO.class);}
    public StaffEntity toStaffEntity(StaffDTO staffDTO){return modelMapper.map(staffDTO, StaffEntity.class);}
    public List<StaffDTO> toStaffDTOList(List<StaffEntity> staffEntities){return staffEntities.stream().map(this::toStaffDTO).toList();}

    //User
    public UserDTO toUserDTO(UserEntity userEntity){return modelMapper.map(userEntity, UserDTO.class);}
    public UserEntity toUserEntity(UserDTO userDTO){return modelMapper.map(userDTO, UserEntity.class);}
    public List<UserDTO> toUserDTOList(List<UserEntity> userEntities){return userEntities.stream().map(this::toUserDTO).toList();}

    //Vehicle
    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity){return modelMapper.map(vehicleEntity, VehicleDTO.class);}
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO){return modelMapper.map(vehicleDTO, VehicleEntity.class);}
    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntities){return vehicleEntities.stream().map(this::toVehicleDTO).toList();}
}
