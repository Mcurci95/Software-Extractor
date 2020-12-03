package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MInterface;
import com.sotwareextractor.cecs547.Repository.MInterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MInterfaceService {
    private final MInterfaceRepository mInterfaceRepository;

    @Autowired
    public MInterfaceService(MInterfaceRepository mInterfaceRepository) {
        this.mInterfaceRepository = mInterfaceRepository;
    }

    public List<MInterface> getOrCreate(List<String> interfaces) {
        List<MInterface> entities = new ArrayList<>();
        for (String inter : interfaces) {
            List<MInterface> existing = mInterfaceRepository.findByName(inter);
            if (existing.size() == 0) {
                MInterface inst = new MInterface();
                inst.setName(inter);
                entities.add(mInterfaceRepository.save(inst));
            }
        }
        return entities;
    }
}
