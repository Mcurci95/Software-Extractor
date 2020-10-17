package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MClassMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType mType;
    @ManyToMany
    private List<MClassMethodParameter> mParameters;
    @OneToMany(mappedBy = "originatedClassMethod")
    private List<MClassMethod> otherClassMethods;
    @ManyToOne
    private MClassMethod originatedClassMethod;

    public MClassMethod() {}
    public MClassMethod(Long id, String name, MAccess mAccess, MType mType, List<MClassMethodParameter> mParameters, List<MClassMethod> otherClassMethods, MClassMethod originatedClassMethod) {
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
    public List<MClassMethodParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<MClassMethodParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public List<MClassMethod> getOtherClassMethods() {
        return otherClassMethods;
    }
    public void setOtherClassMethods(List<MClassMethod> otherClassMethods) {
        this.otherClassMethods = otherClassMethods;
    }
    public MClassMethod getOriginatedClassMethod() {
        return originatedClassMethod;
    }
    public void setOriginatedClassMethod(MClassMethod originatedClassMethod) {
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
    public List<MClassMethodParameter> getParameters() {
        return mParameters;
    }
    public void setParameters(List<MClassMethodParameter> parameters) {
        this.mParameters = parameters;
    }
}
