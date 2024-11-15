package com.example.crop_monitoring_system.dao;

import com.example.crop_monitoring_system.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FieldDAO extends JpaRepository<FieldEntity,String> {
    @Query("select f.fieldCode from FieldEntity f order by f.fieldCode desc limit 1")
    String generateFieldCode();

    @EntityGraph(attributePaths = {"crops", "staffs", "equipments"})
    Optional<FieldEntity> findById(String fieldCode);
}
