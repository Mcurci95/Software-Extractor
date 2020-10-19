package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Repository.MTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MTypeService {

    private MTypeRepository mTypeRepository;
    @Autowired
    public void setmTypeRepository(MTypeRepository mTypeRepository) {
        this.mTypeRepository = mTypeRepository;
    }
}
