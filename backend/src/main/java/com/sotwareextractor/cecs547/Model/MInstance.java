package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @ManyToOne
    private MClass mClass;
    @OneToMany(mappedBy = "mInstance")
    private Set<MInstanceMethod> mInstanceMethods;

    public MInstance() {}
    public MInstance(Long id, String name, MClass mClass, Set<MInstanceMethod> mInstanceMethods) {
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
    public Set<MInstanceMethod> getmInstanceMethods() {
        return mInstanceMethods;
    }
    public void setmInstanceMethods(Set<MInstanceMethod> mInstanceMethods) {
        this.mInstanceMethods = mInstanceMethods;
    }
}
