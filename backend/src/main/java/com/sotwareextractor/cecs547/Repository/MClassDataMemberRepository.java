package com.sotwareextractor.cecs547.Repository;

import com.sotwareextractor.cecs547.Model.MClassDataMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MClassDataMemberRepository extends JpaRepository<MClassDataMember, Long> {
    List<MClassDataMember> findByName(String name);
}
