package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MInterface;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MInterfaceRepository extends JpaRepository<MInterface, Long> {
    List<MInterface> findByName(String name);
}
