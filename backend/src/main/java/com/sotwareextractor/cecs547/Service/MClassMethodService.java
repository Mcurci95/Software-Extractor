package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.unused.MClassMethodParameter;
import com.sotwareextractor.cecs547.POJO.DClassMethod;
import com.sotwareextractor.cecs547.Model.*;
import com.sotwareextractor.cecs547.Repository.MMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MClassMethodService {
    private MAccessService mAccessService;
    private MMethodRepository mMethodRepository;
    private MTypeService mTypeService;
    private MMethodParameterService mClassMethodParameterService;

    @Autowired
    public MClassMethodService(MAccessService mAccessService,
                               MMethodRepository mMethodRepository,
                               MTypeService mTypeService,
                               MMethodParameterService mClassMethodParameterService) {
        this.mAccessService = mAccessService;
        this.mMethodRepository = mMethodRepository;
        this.mTypeService = mTypeService;
        this.mClassMethodParameterService = mClassMethodParameterService;
    }

    public MMethod getOrCreate(DClassMethod method, MClass mClass) {
        List<MMethod> existing = mMethodRepository.findByName(method.getName());
        for (var instance : existing) {
            List<String> accessNames = instance.getmAccess().stream().map(MAccess::getAccessName).collect(Collectors.toList());
            if (instance.getmAccess() != null && accessNames.contains(method.getName())
                    && instance.getmClass() != null &&
                    instance.getmClass().getName().equals(mClass.getName())) {
                return instance;
            }
        }
        String name = method.getName();
        if (name == null) return null;
        List<MAccess> mAccess = mAccessService.getOrCreate(method.getModifiers());
        MType mReturnType = mTypeService.getOrCreate(method.getReturnType());

        List<MMethodParameter> paramEntities = mClassMethodParameterService.getOrCreate(method.getParams());
        MMethod instance = new MMethod(name, mAccess, mReturnType, mClass);
        instance.setmParameters(paramEntities);

        return mMethodRepository.save(instance);
    }
}
