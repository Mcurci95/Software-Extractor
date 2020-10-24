package com.sotwareextractor.cecs547.Model.unused;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MType;

import javax.persistence.*;
import java.util.List;

public class _MClassMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType mType;
    @ManyToMany
    private List<_MClassMethodParameter> mParameters;
    @OneToMany(mappedBy = "originatedClassMethod")
    private List<_MClassMethod> otherClassMethods;
    @ManyToOne
    private _MClassMethod originatedClassMethod;

    public _MClassMethod() {}
    public _MClassMethod(Long id, String name, MAccess mAccess, MType mType, List<_MClassMethodParameter> mParameters, List<_MClassMethod> otherClassMethods, _MClassMethod originatedClassMethod) {
        Id = id;
        this.name = name;
        this.mAccess = mAccess;
        this.mType = mType;
        this.mParameters = mParameters;
        this.otherClassMethods = otherClassMethods;
        this.originatedClassMethod = originatedClassMethod;
    }

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public List<_MClassMethodParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<_MClassMethodParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public List<_MClassMethod> getOtherClassMethods() {
        return otherClassMethods;
    }
    public void setOtherClassMethods(List<_MClassMethod> otherClassMethods) {
        this.otherClassMethods = otherClassMethods;
    }
    public _MClassMethod getOriginatedClassMethod() {
        return originatedClassMethod;
    }
    public void setOriginatedClassMethod(_MClassMethod originatedClassMethod) {
        this.originatedClassMethod = originatedClassMethod;
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
    public List<_MClassMethodParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<_MClassMethodParameter> parameters) {
        this.mParameters = parameters;
    }
}
