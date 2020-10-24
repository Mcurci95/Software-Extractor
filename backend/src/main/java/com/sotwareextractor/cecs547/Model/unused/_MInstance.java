package com.sotwareextractor.cecs547.Model.unused;

import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.unused._MInstanceMethod;

import javax.persistence.*;
import java.util.Set;

public class _MInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;
    @OneToMany(mappedBy = "mInstance", cascade = CascadeType.ALL)
    private Set<_MInstanceMethod> mInstanceMethods;

    public _MInstance() {}
    public _MInstance(Long id, String name, MClass mClass, Set<_MInstanceMethod> mInstanceMethods) {
        Id = id;
        this.name = name;
        this.mClass = mClass;
        this.mInstanceMethods = mInstanceMethods;
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
    public MClass getmClass() {
        return mClass;
    }
    public void setmClass(MClass mClass) {
        this.mClass = mClass;
    }
    public Set<_MInstanceMethod> getmInstanceMethods() {
        return mInstanceMethods;
    }
    public void setmInstanceMethods(Set<_MInstanceMethod> mInstanceMethods) {
        this.mInstanceMethods = mInstanceMethods;
    }
}
