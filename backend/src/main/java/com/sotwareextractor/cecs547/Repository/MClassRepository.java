package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClass;
import org.hibernate.sql.MckoiCaseFragment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MClassRepository extends JpaRepository<MClass, Long> {
    List<MClass> findByName(String name);


    @Query(value = "SELECT distinct NAME FROM MCLASS", nativeQuery = true)
    List<String> findDistinctClassNames();

//    @Query(value = "SELECT id, NAME FROM MCLASS WHERE parent = ")
    List<MClass> findNameByParent(String parent);
}
