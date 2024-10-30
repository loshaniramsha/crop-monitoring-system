package com.example.crop_monitoring_system.entity.impl;

import com.example.crop_monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitoring_log")
public class MonitoringLogEntity implements SuperEntity {
    @Id
    private String logCode;

    private Date logDate;
    private String logDetails;

    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FieldEntity> fields;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CropEntity> crops;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StaffEntity> staffs;

}
