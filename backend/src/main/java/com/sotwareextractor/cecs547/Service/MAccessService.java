package com.sotwareextractor.cecs547.Service;


import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Repository.MAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MAccessService {
    private MAccessRepository mAccessRepository;
    @Autowired
    public void setmAccessRepository(MAccessRepository mAccessRepository) {
        this.mAccessRepository = mAccessRepository;
    }

    public MAccess add(String name) {
        List<MAccess> existing = mAccessRepository.findByAccessName(name);
        if (existing.size() == 0)
            return mAccessRepository.save(new MAccess(name));
        else
            return existing.get(0);
    }

    public MAccess findByName(String name) {
        List<MAccess> existing = mAccessRepository.findByAccessName(name);
        if (existing.size() > 0) return existing.get(0);
        else return null;
    }
}
