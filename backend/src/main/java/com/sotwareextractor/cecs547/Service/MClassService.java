package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.MPackage;
import com.sotwareextractor.cecs547.Repository.MClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MClassService {
    private MClassRepository mClassRepository;
    @Autowired
    public void setmClassRepository(MClassRepository mClassRepository) {
        this.mClassRepository = mClassRepository;
    }

    public MClass add(String name) {
        List<MClass> existing = mClassRepository.findByName(name);
        if (existing.size() == 0)
            return mClassRepository.save(new MClass(name));
        else
            return existing.get(0);
    }

    public MClass findByName(String name) {
        List<MClass> existing = mClassRepository.findByName(name);
        if (existing.size() == 0)
            return null;
        else
            return existing.get(0);
    }
}
