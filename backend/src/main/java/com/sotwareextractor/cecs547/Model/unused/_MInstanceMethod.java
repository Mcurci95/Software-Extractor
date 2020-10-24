package com.sotwareextractor.cecs547.Model.unused;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MType;

import javax.persistence.*;
import java.util.List;

public class _MInstanceMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType returnType;
    @ManyToMany
    private List<_MInstanceParameter> mParameters;
    @ManyToOne
    private _MInstance mInstance;
    @OneToMany(mappedBy = "originatedInstanceMethod")
    private List<_MInstanceMethod> otherInstanceMethods;
    @ManyToOne
    private _MInstanceMethod originatedInstanceMethod;

    public _MInstanceMethod(){}
    public _MInstanceMethod(Long id, String name, MAccess mAccess, MType returnType, List<_MInstanceParameter> mParameters, _MInstance mInstance, List<_MInstanceMethod> otherInstanceMethods, _MInstanceMethod originatedInstanceMethod) {
        Id = id;
        this.name = name;
        this.mAccess = mAccess;
        this.returnType = returnType;
        this.mParameters = mParameters;
        this.mInstance = mInstance;
        this.otherInstanceMethods = otherInstanceMethods;
        this.originatedInstanceMethod = originatedInstanceMethod;
    }

    public List<_MInstanceParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<_MInstanceParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public _MInstance getmInstance() {
        return mInstance;
    }
    public void setmInstance(_MInstance mInstance) {
        this.mInstance = mInstance;
    }
    public List<_MInstanceMethod> getOtherInstanceMethods() {
        return otherInstanceMethods;
    }
    public void setOtherInstanceMethods(List<_MInstanceMethod> otherInstanceMethods) {
        this.otherInstanceMethods = otherInstanceMethods;
    }
    public _MInstanceMethod getOriginatedInstanceMethod() {
        return originatedInstanceMethod;
    }
    public void setOriginatedInstanceMethod(_MInstanceMethod originatedInstanceMethod) {
        this.originatedInstanceMethod = originatedInstanceMethod;
    }
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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
    public MType getReturnType() {
        return returnType;
    }
    public void setReturnType(MType returnType) {
        this.returnType = returnType;
    }
    public List<_MInstanceParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<_MInstanceParameter> parameters) {
        this.mParameters = parameters;
    }
}
