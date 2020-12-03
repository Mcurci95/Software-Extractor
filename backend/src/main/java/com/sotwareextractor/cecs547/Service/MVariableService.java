package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MMethodBody;
import com.sotwareextractor.cecs547.Model.MVariable;
import com.sotwareextractor.cecs547.POJO.DVariable;
import com.sotwareextractor.cecs547.Repository.MVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MVariableService {
    private final MVariableRepository mVariableRepository;
    private final MTypeService mTypeService;

    @Autowired
    public MVariableService(MVariableRepository mVariableRepository, MTypeService mTypeService) {
        this.mVariableRepository = mVariableRepository;
        this.mTypeService = mTypeService;
    }

    public MVariable getOrCreate(DVariable variable, MMethodBody mMethodBody) {
        MVariable instance = new MVariable();
        instance.setName(variable.getName());
        instance.setmReturnType(mTypeService.getOrCreate(variable.getType()));
        if (variable.getValue() != null) instance.setValue(variable.getValue()[1]);
        instance.setmMethodBody(mMethodBody);

        return mVariableRepository.save(instance);
    }
}
