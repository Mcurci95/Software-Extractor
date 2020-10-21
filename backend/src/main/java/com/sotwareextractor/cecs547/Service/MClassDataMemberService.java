package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClassDataMember;
import com.sotwareextractor.cecs547.Model.MType;
import com.sotwareextractor.cecs547.Repository.MClassDataMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MClassDataMemberService {
    private MClassDataMemberRepository mClassDataMemberRepository;
    @Autowired
    public void setmClassDataMemberRepository(MClassDataMemberRepository mClassDataMemberRepository) {
        this.mClassDataMemberRepository = mClassDataMemberRepository;
    }

    public MClassDataMember add(String name, MAccess mAccess, MType mType) {
        return mClassDataMemberRepository.save(
                new MClassDataMember(name, mAccess, mType));
    }
}
