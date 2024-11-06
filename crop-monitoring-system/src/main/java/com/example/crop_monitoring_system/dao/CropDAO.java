package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropDAO extends JpaRepository<CropEntity,String> {
    @Query("select c.cropCode from CropEntity c order by c.cropCode desc limit 1")
    String generateCropCode();
}
