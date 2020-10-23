package com.sotwareextractor.cecs547.Service;


import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Repository.MAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MAccessService {
    private MAccessRepository mAccessRepository;
    @Autowired
    public void setmAccessRepository(MAccessRepository mAccessRepository) {
        this.mAccessRepository = mAccessRepository;
    }

    public List<MAccess> getOrCreate(List<String> name) {
        List<MAccess> access = new ArrayList<>();
        for (String each : name) {
            List<MAccess> existing = mAccessRepository.findByAccessName(each);
            if (existing.size() > 0) access.add(existing.get(0));
            else access.add(mAccessRepository.save(new MAccess(each)));
        }
        return access;
    }
}
