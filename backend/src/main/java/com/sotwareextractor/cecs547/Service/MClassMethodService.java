package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.DAO.DClassMethod;
import com.sotwareextractor.cecs547.Model.*;
import com.sotwareextractor.cecs547.Repository.MMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MClassMethodService {
    private MAccessService mAccessService;
    private MMethodRepository mMethodRepository;
    private MTypeService mTypeService;

    @Autowired
    public MClassMethodService(MAccessService mAccessService, MMethodRepository mMethodRepository, MTypeService mTypeService) {
        this.mAccessService = mAccessService;
        this.mMethodRepository = mMethodRepository;
        this.mTypeService = mTypeService;
    }

    public MMethod add(DClassMethod method, MClass mClass) {
        List<MMethod> existing = mMethodRepository.findByName(method.getName());
        for (var instance : existing) {
            if (instance.getmAccess() != null && instance.getmAccess().getAccessName().equals(method.getName())
                    && instance.getmClass() != null &&
                    instance.getmClass().getName().equals(mClass.getName())) {
                return instance;
            }
        }
        String name = method.getName();
        MAccess mAccess = mAccessService.getOrCreate(method.getAccessLevel());
        MType mReturnType = mTypeService.getOrCreate(method.getReturnType());
        return mMethodRepository.save(new MMethod(name, mAccess, mReturnType, mClass));
    }
}
