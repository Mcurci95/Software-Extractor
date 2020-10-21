package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;

@Entity
public class MClassDataMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MClass mClass;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MType mType;

    public MClassDataMember() {
    }
    public MClassDataMember(String name, MAccess mAccess, MType mType) {
        this.name = name;
        this.mAccess = mAccess;
        this.mType = mType;
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
}
