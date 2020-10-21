package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MType;
import com.sotwareextractor.cecs547.Repository.MTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MTypeService {

    private MTypeRepository mTypeRepository;
    @Autowired
    public void setmTypeRepository(MTypeRepository mTypeRepository) {
        this.mTypeRepository = mTypeRepository;
    }

    public MType getOrCreate(String name) {
        List<MType> existing = mTypeRepository.findByName(name);
        if (existing.size() > 0) return existing.get(0);
        else return mTypeRepository.save(new MType(name));
    }
}
