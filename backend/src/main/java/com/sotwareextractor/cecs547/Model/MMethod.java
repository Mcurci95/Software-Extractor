package com.sotwareextractor.cecs547.Model;

import com.sotwareextractor.cecs547.Model.unused.MClassMethodParameter;

import javax.persistence.*;
import java.util.List;

@Entity
public class MMethod {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType mType;
    @ManyToMany
    private List<MClassMethodParameter> mParameters;
    @ManyToOne
    private MClass mClass;

    public MMethod() {
    }
    public MMethod(String name, MAccess mAccess, MClass mClass) {
        this.name = name;
        this.mAccess = mAccess;
        this.mClass = mClass;
    }

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public List<MClassMethodParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<MClassMethodParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MAccess getmAccess() {
        return mAccess;
    }
    public void setmAccess(MAccess mAccess) {
        this.mAccess = mAccess;
    }
    public MType getmType() {
        return mType;
    }
    public void setmType(MType mType) {
        this.mType = mType;
    }
    public List<MClassMethodParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<MClassMethodParameter> parameters) {
        this.mParameters = parameters;
    }
}
