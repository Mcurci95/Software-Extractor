package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class MClassDataMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<MAccess> mAccess;

    @JsonManagedReference
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

    public MClass getmClass() {
        return mClass;
    }

    public void setmClass(MClass mClass) {
        this.mClass = mClass;
    }
}
