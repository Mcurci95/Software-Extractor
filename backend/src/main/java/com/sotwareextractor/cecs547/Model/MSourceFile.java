package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
public class MSourceFile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;

    private String fileType;
    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    private MClass mClass;


    public MSourceFile(){}
    public MSourceFile(Long id, String fileName, String fileType, byte[] data) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    public MSourceFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


    @JsonIgnore
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @JsonIgnore
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}
