package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MMethodRepository extends JpaRepository<MMethod, Long> {
    List<MMethod> findByName(String name);
}
