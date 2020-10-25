package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
public class MClassDataMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<MAccess> mAccess;
    @ManyToOne(cascade = CascadeType.ALL)
    private MType mType;

    public MClassDataMember() {
    }
    public MClassDataMember(String name, List<MAccess> mAccess, MType mType, MClass mClass) {
        this.name = name;
        this.mAccess = mAccess;
        this.mType = mType;
        this.mClass = mClass;
    }

    public List<MAccess> getmAccess() {
        return mAccess;
    }
    public void setmAccess(List<MAccess> mAccess) {
        this.mAccess = mAccess;
    }

    public MType getmType() {
        return mType;
    }
    public void setmType(MType mType) {
        this.mType = mType;
    }

    @JsonIgnore
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
