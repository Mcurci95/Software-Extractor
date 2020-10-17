package com.sotwareextractor.cecs547.Model;

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
    private List<MInstanceParameter> parameters;

    public MInstanceMethod(){}
    public MInstanceMethod(Long id, String name, MAccess mAccess, MType returnType, List<MInstanceParameter> parameters) {
        Id = id;
        this.name = name;
        this.mAccess = mAccess;
        this.returnType = returnType;
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

    public MType getReturnType() {
        return returnType;
    }

    public void setReturnType(MType returnType) {
        this.returnType = returnType;
    }

    public List<MInstanceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<MInstanceParameter> parameters) {
        this.parameters = parameters;
    }
}
