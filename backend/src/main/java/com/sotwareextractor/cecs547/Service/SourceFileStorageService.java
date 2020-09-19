package com.sotwareextractor.cecs547.Service;

import com.sotwareextractor.cecs547.Model.SourceFile;
import com.sotwareextractor.cecs547.Repository.SourceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SourceFileStorageService {

    @Autowired
    private SourceFileRepository sourceFileRepository;

    public SourceFile saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        SourceFile sourceFile = new SourceFile(fileName, file.getContentType(), file.getBytes());
        return sourceFileRepository.save(sourceFile);
    }

    public Optional<SourceFile> getFile(Integer sourceFileId) {
        return sourceFileRepository.findById(sourceFileId);
    }

    public List<SourceFile> getFiles() {
        return sourceFileRepository.findAll();
    }
}
