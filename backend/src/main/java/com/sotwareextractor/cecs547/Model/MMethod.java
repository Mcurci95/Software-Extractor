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
    @ManyToOne(cascade = CascadeType.ALL)
    private MAccess mAccess;
    @ManyToOne(cascade = CascadeType.ALL)
    private MType mReturnType;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<MClassMethodParameter> mParameters;
    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;

    public MMethod() {
    }
    public MMethod(String name, MAccess mAccess, MType mType, MClass mClass) {
        this.name = name;
        this.mAccess = mAccess;
        this.mClass = mClass;
        this.mReturnType = mType;
    }

    public MClass getmClass() {
        return mClass;
    }
    public void setmClass(MClass mClass) {
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
    public MType getmMReturnType() {
        return mReturnType;
    }
    public void setmMReturnType(MType mType) {
        this.mReturnType = mType;
    }
    public List<MClassMethodParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<MClassMethodParameter> parameters) {
        this.mParameters = parameters;
    }
}
