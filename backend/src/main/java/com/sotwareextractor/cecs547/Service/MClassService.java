package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MClass;
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

    public void addClass(String name) {
        List<MClass> existing = mClassRepository.findByName(name);
    }
}
