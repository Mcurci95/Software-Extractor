package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Embedded
    private MAccess mAccess;

    @ManyToOne
    private MPackage mPackage; // package is a reserved word

    public MClass(String name, com.sotwareextractor.cecs547.Model.MAccess MAccess, MPackage mPackage) {
        this.name = name;
        this.mAccess = MAccess;
        this.mPackage = mPackage;
    }
    public MClass() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MPackage getmPackage() {
        return mPackage;
    }

    public void setmPackage(MPackage mPackage) {
        this.mPackage = mPackage;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MAccess getMAccess() {
        return mAccess;
    }
    public void setMAccess(MAccess MAccess) {
        this.mAccess = MAccess;
    }
    public MPackage getClassMPackage() {
        return mPackage;
    }
    public void setClassMPackage(MPackage classMPackage) {
        this.mPackage = classMPackage;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", access=" + mAccess +
                '}';
    }

}
