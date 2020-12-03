package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MMethod;
import com.sotwareextractor.cecs547.Model.MMethodParameter;
import com.sotwareextractor.cecs547.Model.MType;
import com.sotwareextractor.cecs547.POJO.DMethodParam;
import com.sotwareextractor.cecs547.Repository.MMethodParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MMethodParameterService {
    private final MMethodParamRepository repository;
    private final MTypeService mTypeService;

    @Autowired
    public MMethodParameterService(MMethodParamRepository repository, MTypeService mTypeService) {
        this.repository = repository;
        this.mTypeService = mTypeService;
    }

    public List<MMethodParameter> getOrCreate(List<DMethodParam> params, MMethod mMethod) {
        List<MMethodParameter> paramEntities = new ArrayList<>();
        for (DMethodParam param : params) {
            String name = param.getName();
            int order = param.getOrder();
            MType type = mTypeService.getOrCreate(param.getType());
            MMethodParameter entity = repository.save(new MMethodParameter(name, order, type, mMethod));
            paramEntities.add(entity);
        }
        return paramEntities;
    }
}
