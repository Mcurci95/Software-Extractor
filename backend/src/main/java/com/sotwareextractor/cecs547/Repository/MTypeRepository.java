package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MTypeRepository extends JpaRepository<MType, Long> {
    List<MType> findByName(String name);
}
