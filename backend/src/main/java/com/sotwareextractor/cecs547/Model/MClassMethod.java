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
    private List<MClassMethodParameter> parameters;

    public MClassMethod() {}

    public MClassMethod(String name, MAccess mAccess, MType mType, List<MClassMethodParameter> parameters) {
        this.name = name;
        this.mAccess = mAccess;
        this.mType = mType;
        this.parameters = parameters;
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

    public MType getmType() {
        return mType;
    }

    public void setmType(MType mType) {
        this.mType = mType;
    }

    public List<MClassMethodParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<MClassMethodParameter> parameters) {
        this.parameters = parameters;
    }
}
