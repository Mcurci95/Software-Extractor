package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MMethodParameter;
import com.sotwareextractor.cecs547.Model.MType;
import com.sotwareextractor.cecs547.POJO.DMethodParam;
import com.sotwareextractor.cecs547.Repository.MMethodParamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MMethodParameterService {
    private MMethodParamRepository repository;
    private MTypeService mTypeService;

    public List<MMethodParameter> getOrCreate(List<DMethodParam> params) {
        List<MMethodParameter> paramEntities = new ArrayList<>();
        for (DMethodParam param : params) {
            String name = param.getName();
            int order = param.getOrder();
            MType type = mTypeService.getOrCreate(param.getType());
            MMethodParameter entity = repository.save(new MMethodParameter(param.getName(), param.getOrder(), type));
            paramEntities.add(entity);
        }
        return paramEntities;
    }
}
