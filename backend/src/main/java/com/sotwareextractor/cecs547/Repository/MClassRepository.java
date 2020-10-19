package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MClassRepository extends JpaRepository<MClass, Long> {
    List<MClass> findByName(String name);
}
