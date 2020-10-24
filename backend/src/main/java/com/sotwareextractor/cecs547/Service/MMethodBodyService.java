package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MMethodBody;
import com.sotwareextractor.cecs547.Model.MMethodCall;
import com.sotwareextractor.cecs547.Model.MVariable;
import com.sotwareextractor.cecs547.POJO.DMethodStatement;
import com.sotwareextractor.cecs547.POJO.DVariable;
import com.sotwareextractor.cecs547.Repository.MMethodBodyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MMethodBodyService {
    private MMethodBodyRespository mMethodBodyRespository;
    private MVariableService mVariableService;
    private MMethodCallService mMethodCallService;
    private MTypeService mTypeService;

    @Autowired
    public MMethodBodyService(MMethodBodyRespository mMethodBodyRespository, MVariableService mVariableService,
                              MMethodCallService mMethodCallService, MTypeService mTypeService) {
        this.mMethodBodyRespository = mMethodBodyRespository;
        this.mVariableService = mVariableService;
        this.mMethodCallService = mMethodCallService;
        this.mTypeService = mTypeService;
    }

    public MMethodBody getOrCreate(List<DVariable> variables, List<String> methodCalls) {
        MMethodBody mMethodBody = new MMethodBody();

        List<MVariable> varEntities = new ArrayList<>();
        for (var variable : variables) {
            MVariable variableInstance = new MVariable();
//            variableInstance.setName(variable.getName());
//            variableInstance.setmReturnType(mTypeService.getOrCreate(variable.getType()));
//            variableInstance.setValue(variable.getValue()[1]);
//            variableInstance.setmMethodBody(mMethodBody);
            MVariable varEntity = mVariableService.getOrCreate(variable, mMethodBody);
            varEntities.add(varEntity);
        }
        mMethodBody.setVariables(varEntities);

        List<MMethodCall> methodCallEntities = new ArrayList<>();
        for (var methodCall : methodCalls) {
            MMethodCall mMethodCallInst = new MMethodCall();
//            mMethodCallInst.parseCall(methodCall);
//            mMethodCallInst.setmMethodBody(mMethodBody);
            MMethodCall callEntity = mMethodCallService.getOrCreate(methodCall, mMethodBody);
            methodCallEntities.add(callEntity);
        }
        mMethodBody.setMethodCalls(methodCallEntities);

        return mMethodBodyRespository.save(mMethodBody);
    }
}
