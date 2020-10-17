package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class MClassMethodParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private int mOrder;
    @ManyToOne
    private MType mType;
    @ManyToMany(mappedBy = "mParameters")
    private Collection<MClassMethod> mClassMethods;

    public MClassMethodParameter() {}
    public MClassMethodParameter(long id, String name, int mOrder, MType mType, Collection<MClassMethod> mClassMethods) {
        Id = id;
        this.name = name;
        this.mOrder = mOrder;
        this.mType = mType;
        this.mClassMethods = mClassMethods;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public MType getType() {
        return mType;
    }
    public void setType(MType type) {
        this.mType = type;
    }
    public int getOrder() {
        return mOrder;
    }
    public void setOrder(int order) {
        this.mOrder = order;
    }
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public int getmOrder() {
        return mOrder;
    }
    public void setmOrder(int mOrder) {
        this.mOrder = mOrder;
    }
    public MType getmType() {
        return mType;
    }
    public void setmType(MType mType) {
        this.mType = mType;
    }
    public Collection<MClassMethod> getmClassMethods() {
        return mClassMethods;
    }
    public void setmClassMethods(Collection<MClassMethod> mClassMethods) {
        this.mClassMethods = mClassMethods;
    }
}
