package main.java.com.sotwareextractor.cecs547.Service;

import main.java.com.sotwareextractor.cecs547.Repository.SourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceFileStorageService {

    @Autowired
    private SourceFileRepository sourceFileRepository;
}
