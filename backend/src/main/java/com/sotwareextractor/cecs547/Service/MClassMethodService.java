package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.POJO.DClassMethod;
import com.sotwareextractor.cecs547.Model.*;
import com.sotwareextractor.cecs547.POJO.DVariable;
import com.sotwareextractor.cecs547.Repository.MMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MClassMethodService {
    private MAccessService mAccessService;
    private MMethodRepository mMethodRepository;
    private MTypeService mTypeService;
    private MMethodParameterService mClassMethodParameterService;
    private MMethodBodyService mMethodBodyService;

    @Autowired
    public MClassMethodService(MAccessService mAccessService,
                               MMethodRepository mMethodRepository,
                               MTypeService mTypeService,
                               MMethodParameterService mClassMethodParameterService,
                               MMethodBodyService mMethodBodyService) {
        this.mAccessService = mAccessService;
        this.mMethodRepository = mMethodRepository;
        this.mTypeService = mTypeService;
        this.mClassMethodParameterService = mClassMethodParameterService;
        this.mMethodBodyService = mMethodBodyService;
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

        MMethod instance = new MMethod(name, mAccess, mReturnType, mClass);
        List<MMethodParameter> paramEntities = mClassMethodParameterService.getOrCreate(method.getParams(), instance);
        instance.setmParameters(paramEntities);

        MMethodBody bodyEntity = mMethodBodyService.getOrCreate(new ArrayList<>(method.getVariables().values()),
                method.getMethodCalls());
        instance.setmBody(bodyEntity);

        return mMethodRepository.save(instance);
    }
}
