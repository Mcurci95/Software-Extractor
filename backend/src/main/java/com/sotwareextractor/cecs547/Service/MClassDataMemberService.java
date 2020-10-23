package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.POJO.DClassField;
import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClassDataMember;
import com.sotwareextractor.cecs547.Model.MType;
import com.sotwareextractor.cecs547.Repository.MClassDataMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MClassDataMemberService {
    private MAccessService mAccessService;
    private MClassDataMemberRepository mClassDataMemberRepository;
    private MTypeService mTypeService;

    @Autowired
    public MClassDataMemberService(MAccessService mAccessService, MClassDataMemberRepository mClassDataMemberRepository, MTypeService mTypeService) {
        this.mAccessService = mAccessService;
        this.mClassDataMemberRepository = mClassDataMemberRepository;
        this.mTypeService = mTypeService;
    }

    public MClassDataMember getOrCreate(DClassField field) {
        List<MClassDataMember> existing = mClassDataMemberRepository.findByName(field.getName());
        for (var instance : existing) {
            List<String> accessNames = instance.getmAccess().stream().map(MAccess::getAccessName).collect(Collectors.toList());

            if (instance.getmAccess() != null &&
                    accessNames.contains(field.getName()) &&
                    instance.getmType() != null &&
                    instance.getmType().getName().equals(field.getType())) {
                return instance;
            }
        }
        String name = field.getName();
        List<MAccess> mAccess = mAccessService.getOrCreate(field.getModifiers());
        MType mType = mTypeService.getOrCreate(field.getType());
        return mClassDataMemberRepository.save(new MClassDataMember(name, mAccess, mType));
    }
}
