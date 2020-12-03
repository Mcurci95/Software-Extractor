package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.MConstructor;
import com.sotwareextractor.cecs547.Model.MConstructorParameter;
import com.sotwareextractor.cecs547.POJO.DConstructor;
import com.sotwareextractor.cecs547.Repository.MConstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MConstructorService {
    private final MConstructorRepository mConstructorRepository;
    private final MConstructorParamService mConstructorParamService;
    private final MTypeService mTypeService;

    @Autowired
    public MConstructorService(MConstructorRepository mConstructorRepository,
                               MConstructorParamService mConstructorParamService,
                               MTypeService mTypeService) {
        this.mConstructorRepository = mConstructorRepository;
        this.mConstructorParamService = mConstructorParamService;
        this.mTypeService = mTypeService;
    }

    public List<MConstructor> getOrCreate(List<DConstructor> dConstructor, MClass mClass) {
        List<MConstructor> mConstructors = new ArrayList<>();

        for (var constructor : dConstructor) {
            MConstructor mConstructor = new MConstructor();
            List<MConstructorParameter> mConstructorParameters = mConstructorParamService.getOrCreate(constructor.getParameters(),
                    mConstructor);
            mConstructor.setParameters(mConstructorParameters);
            mConstructor.setName(constructor.getName());
            mConstructor.setType(mTypeService.getOrCreate(constructor.getType()));
            mConstructor.setmClass(mClass);

            mConstructors.add(mConstructorRepository.save(mConstructor));
        }
        return mConstructors;
    }
}
