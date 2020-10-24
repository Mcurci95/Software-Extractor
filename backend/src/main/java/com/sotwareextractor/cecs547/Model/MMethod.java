package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;


@Entity
public class MMethod {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<MAccess> mAccess;

    @ManyToOne(cascade = CascadeType.ALL)
    private MType mReturnType;

    @OneToMany(mappedBy = "mMethod", cascade = CascadeType.ALL)
    private List<MMethodParameter> mParameters;


    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;


    @OneToOne(cascade = CascadeType.ALL)
    private MMethodBody mBody;

    public MMethod() {
    }
    public MMethod(String name, List<MAccess> mAccess, MType mType, MClass mClass) {
        this.name = name;
        this.mAccess = mAccess;
        this.mClass = mClass;
        this.mReturnType = mType;
    }

    @JsonIgnore
    public MClass getmClass() {
        return mClass;
    }
    public void setmClass(MClass mClass) {
        this.mClass = mClass;
    }

    @JsonIgnore
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }

    @JsonIgnore
    public List<MMethodParameter> getmParameters() {
        return mParameters;
    }
    public void setmParameters(List<MMethodParameter> mParameters) {
        this.mParameters = mParameters;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<MAccess> getmAccess() {
        return mAccess;
    }
    public void setmAccess(List<MAccess> mAccess) {
        this.mAccess = mAccess;
    }

    @JsonIgnore
    public MType getmMReturnType() {
        return mReturnType;
    }
    public void setmMReturnType(MType mType) {
        this.mReturnType = mType;
    }

    @JsonIgnore
    public MType getmReturnType() {
        return mReturnType;
    }
    public void setmReturnType(MType mReturnType) {
        this.mReturnType = mReturnType;
    }

    @JsonIgnore
    public MMethodBody getmBody() {
        return mBody;
    }
    public void setmBody(MMethodBody mBody) {
        this.mBody = mBody;
    }
}
