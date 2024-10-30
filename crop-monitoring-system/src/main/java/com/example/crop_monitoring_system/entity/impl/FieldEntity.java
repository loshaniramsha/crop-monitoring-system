package com.example.crop_monitoring_system.entity.impl;


import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String fieldCode;

    private String fieldName;

    private Point fieldLocation;

    private Double extentSize;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CropEntity> crops;

    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> staffs;

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<EquipmentEntity> equipments;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;

    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;

    @ManyToOne
    @JoinColumn(name = "logId",nullable = false)
    private MonitoringLogEntity log;

}
