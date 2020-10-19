package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private MAccess mAccess;
    @ManyToOne
    private MPackage mPackage; // package is a reserved word
    @OneToMany(mappedBy = "parent")
    private Set<MClass> childClasses;
    @ManyToOne
    private MClass parent;
    @OneToMany(mappedBy = "mClass")
    private Set<MInstance> mInstances;

    public MClass() {}
    public MClass(Long id, String name, MAccess mAccess, MPackage mPackage, Set<MClass> childClasses, MClass parent, Set<MInstance> mInstances) {
        this.id = id;
        this.name = name;
        this.mAccess = mAccess;
        this.mPackage = mPackage;
        this.childClasses = childClasses;
        this.parent = parent;
        this.mInstances = mInstances;
    }
    public MClass(String name) {
        this.name = name;
    }

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
    public MAccess getmAccess() {
        return mAccess;
    }
    public void setmAccess(MAccess mAccess) {
        this.mAccess = mAccess;
    }
    public Set<MClass> getChildClasses() {
        return childClasses;
    }
    public void setChildClasses(Set<MClass> childClasses) {
        this.childClasses = childClasses;
    }
    public MClass getParent() {
        return parent;
    }
    public void setParent(MClass parent) {
        this.parent = parent;
    }
    public Set<MInstance> getmInstances() {
        return mInstances;
    }
    public void setmInstances(Set<MInstance> mInstances) {
        this.mInstances = mInstances;
    }
}
