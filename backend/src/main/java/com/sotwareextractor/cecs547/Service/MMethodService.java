package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.MMethod;
import com.sotwareextractor.cecs547.Repository.MAccessRepository;
import com.sotwareextractor.cecs547.Repository.MClassRepository;
import com.sotwareextractor.cecs547.Repository.MMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MMethodService {
    private MMethodRepository mMethodRepository;
    @Autowired
    public void setmMethodRepository(MMethodRepository mMethodRepository) {
        this.mMethodRepository = mMethodRepository;
    }

    private MAccessService mAccessService;
    @Autowired
    public void setmAccessRepository(MAccessService mAccessService) {
        this.mAccessService = mAccessService;
    }

    private MClassService mClassService;
    @Autowired
    public void setmClassRepository(MClassService mClassService) {
        this.mClassService = mClassService;
    }

    public MMethod add(String name, String modifier, String className) {
        return mMethodRepository.save(new MMethod(name,
                mAccessService.findByName(modifier),
                mClassService.findByName(className)));
    }
}
