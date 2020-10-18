package com.sotwareextractor.cecs547.Model.unused;

import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MInstance;
import com.sotwareextractor.cecs547.Model.MInstanceParameter;
import com.sotwareextractor.cecs547.Model.MType;

import javax.persistence.*;
import java.util.List;

@Entity
public class MInstanceMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType returnType;
    @ManyToMany
    private List<MInstanceParameter> mParameters;
    @ManyToOne
    private MInstance mInstance;
    @OneToMany(mappedBy = "originatedInstanceMethod")
    private List<MInstanceMethod> otherInstanceMethods;
    @ManyToOne
    private MInstanceMethod originatedInstanceMethod;

    public MInstanceMethod(){}
    public MInstanceMethod(Long id, String name, MAccess mAccess, MType returnType, List<MInstanceParameter> mParameters, MInstance mInstance, List<MInstanceMethod> otherInstanceMethods, MInstanceMethod originatedInstanceMethod) {
        Id = id;
        this.name = name;
        this.mAccess = mAccess;
        this.returnType = returnType;
        this.mParameters = mParameters;
        this.mInstance = mInstance;
        this.otherInstanceMethods = otherInstanceMethods;
        this.originatedInstanceMethod = originatedInstanceMethod;
    }

    public List<MInstanceParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<MInstanceParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public MInstance getmInstance() {
        return mInstance;
    }
    public void setmInstance(MInstance mInstance) {
        this.mInstance = mInstance;
    }
    public List<MInstanceMethod> getOtherInstanceMethods() {
        return otherInstanceMethods;
    }
    public void setOtherInstanceMethods(List<MInstanceMethod> otherInstanceMethods) {
        this.otherInstanceMethods = otherInstanceMethods;
    }
    public MInstanceMethod getOriginatedInstanceMethod() {
        return originatedInstanceMethod;
    }
    public void setOriginatedInstanceMethod(MInstanceMethod originatedInstanceMethod) {
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
    public List<MInstanceParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<MInstanceParameter> parameters) {
        this.mParameters = parameters;
    }
}
