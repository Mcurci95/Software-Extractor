package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.*;
import com.sotwareextractor.cecs547.Model.unused._MInstance;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class MClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<MAccess> mAccess;
    @ManyToOne(cascade = CascadeType.ALL)
    private MPackage mPackage; // package is a reserved word
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<MClass> childClasses;
    @ManyToOne(cascade = CascadeType.ALL)
    private MClass parent;
    @OneToMany(mappedBy = "mClass", cascade = CascadeType.ALL)
    private List<MClassDataMember> mClassDataMembers;
    @OneToMany(mappedBy = "mClass", cascade = CascadeType.ALL)
    private List<MMethod> mMethods;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<MInterface> implementInterfaces;
    @OneToMany(mappedBy = "mClass", cascade = CascadeType.ALL)
    private List<MConstructor> mConstructors;

    public MClass() {}
    public MClass(String name, List<MAccess> mAccess, MPackage mPackage) {
        this.name = name;
        this.mAccess = mAccess;
        this.mPackage = mPackage;
    }

    public List<MConstructor> getmConstructors() {
        return mConstructors;
    }
    public void setmConstructors(List<MConstructor> mConstructors) {
        this.mConstructors = mConstructors;
    }
    public List<MMethod> getmMethods() {
        return mMethods;
    }
    public void setmMethods(List<MMethod> mMethods) {
        this.mMethods = mMethods;
    }
    public List<MClassDataMember> getmClassDataMembers() {
        return mClassDataMembers;
    }
    public void setmClassDataMembers(List<MClassDataMember> mClassDataMembers) {
        this.mClassDataMembers = mClassDataMembers;
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
    public MPackage getClassMPackage() {
        return mPackage;
    }
    public void setClassMPackage(MPackage classMPackage) {
        this.mPackage = classMPackage;
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
    public List<MAccess> getmAccess() {
        return mAccess;
    }
    public void setmAccess(List<MAccess> mAccess) {
        this.mAccess = mAccess;
    }
    public List<MInterface> getImplementInterfaces() {
        return implementInterfaces;
    }
    public void setImplementInterfaces(List<MInterface> implementInterfaces) {
        this.implementInterfaces = implementInterfaces;
    }
}
