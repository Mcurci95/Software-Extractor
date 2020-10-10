package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.MSourceFile;
import com.sotwareextractor.cecs547.Repository.MSourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SourceFileStorageService {

    @Autowired
    private MSourceFileRepository sourceFileRepository;

    public MSourceFile saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        MSourceFile sourceFile = new MSourceFile(fileName, file.getContentType(), file.getBytes());
        return sourceFileRepository.save(sourceFile);
    }

    public Optional<MSourceFile> getFile(Long sourceFileId) {
        return sourceFileRepository.findById(sourceFileId);
    }

    public List<MSourceFile> getFiles() {
        return sourceFileRepository.findAll();
    }
}
