package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MConstructor;
import com.sotwareextractor.cecs547.Model.MConstructorParameter;
import com.sotwareextractor.cecs547.POJO.DConstructorParameter;
import com.sotwareextractor.cecs547.Repository.MConstructorParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MConstructorParamService {
    private MConstructorParamRepository mConstructorParamRepository;
    private MTypeService mTypeService;

    @Autowired
    public MConstructorParamService(MConstructorParamRepository mConstructorParamRepository, MTypeService mTypeService) {
        this.mConstructorParamRepository = mConstructorParamRepository;
        this.mTypeService = mTypeService;
    }

    public List<MConstructorParameter> getOrCreate(List<DConstructorParameter> dConstructorParameter, MConstructor mConstructor) {
        List<MConstructorParameter> mConstructorParameters = new ArrayList<>();

        for (var param : dConstructorParameter) {
            MConstructorParameter mConstructorParameter = new MConstructorParameter();
            mConstructorParameter.setName(param.getName());
            mConstructorParameter.setmType(mTypeService.getOrCreate(param.getType()));
            mConstructorParameter.setmOrder(param.getOrder());
            mConstructorParameter.setmConstructor(mConstructor);

            mConstructorParameters.add(mConstructorParamRepository.save(mConstructorParameter));
        }

        return mConstructorParameters;
    }
}
