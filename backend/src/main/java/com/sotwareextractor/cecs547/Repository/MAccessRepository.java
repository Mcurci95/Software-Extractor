package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MAccessRepository extends JpaRepository<MAccess, Long> {
     List<MAccess> findByAccessName(String name);
}
