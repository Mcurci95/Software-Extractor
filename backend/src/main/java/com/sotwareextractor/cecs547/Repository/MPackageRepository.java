package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MPackageRepository extends JpaRepository<MPackage, Long> {
    List<MPackage> findByName(String name);
}
