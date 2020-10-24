package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MMethodBody;
import com.sotwareextractor.cecs547.Model.MMethodCall;
import com.sotwareextractor.cecs547.Model.MVariable;
import com.sotwareextractor.cecs547.Repository.MMethodCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MMethodCallService {
    private MMethodCallRepository mMethodCallRepository;
    @Autowired
    public MMethodCallService(MMethodCallRepository mMethodCallRepository) {
        this.mMethodCallRepository = mMethodCallRepository;
    }

    public MMethodCall getOrCreate(String method, MMethodBody mMethodBody) {
        MMethodCall instance = new MMethodCall();
        instance.parseCall(method);
        instance.setmMethodBody(mMethodBody);

        return mMethodCallRepository.save(instance);
    }
}
