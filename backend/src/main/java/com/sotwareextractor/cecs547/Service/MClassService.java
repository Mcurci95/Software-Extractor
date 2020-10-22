package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.POJO.DClass;
import com.sotwareextractor.cecs547.POJO.DClassField;
import com.sotwareextractor.cecs547.POJO.DClassMethod;
import com.sotwareextractor.cecs547.Model.*;
import com.sotwareextractor.cecs547.Repository.MClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MClassService {
    private MClassRepository mClassRepository;
    private MAccessService mAccessService;
    private MPackageService mPackageService;
    private MClassDataMemberService mClassDataMemberService;
    private MClassMethodService mClassMethodService;

    @Autowired
    public MClassService(MClassRepository mClassRepository, MAccessService mAccessService,
                         MPackageService mPackageService, MClassDataMemberService mClassDataMemberService,
                         MClassMethodService mClassMethodService) {
        this.mClassRepository = mClassRepository;
        this.mAccessService = mAccessService;
        this.mPackageService = mPackageService;
        this.mClassDataMemberService = mClassDataMemberService;
        this.mClassMethodService = mClassMethodService;
    }

    public MClass getOrCreate(DClass dClass) {
        List<MClass> existing = mClassRepository.findByName(dClass.getName());
        if (existing.size() == 0) {
            String className = dClass.getName();
            MAccess mAccess = mAccessService.getOrCreate(dClass.getAccessLevel());
            MPackage mPackage = mPackageService.getOrCreate(dClass.getPackageName());

            MClass mClass = new MClass(className, mAccess, mPackage);

            List<MClassDataMember> mClassDataMembers = new ArrayList<>();
            for (DClassField field : dClass.getFields()) {
                MClassDataMember mClassDataMember = mClassDataMemberService.getOrCreate(field);
                mClassDataMembers.add(mClassDataMember);
            }
            mClass.setmClassDataMembers(mClassDataMembers);

            List<MMethod> mMethods = new ArrayList<>();
            for (DClassMethod method : dClass.getdClassMethods()) {
                MMethod mMethod = mClassMethodService.getOrCreate(method, mClass);
                if (mMethod != null) mMethods.add(mMethod);
            }
            mClass.setmMethods(mMethods);

            return mClassRepository.save(mClass);
        }

        else
            return existing.get(0);
    }

    public MClass findByName(String name) {
        List<MClass> existing = mClassRepository.findByName(name);
        if (existing.size() == 0)
            return null;
        else
            return existing.get(0);
    }
}
