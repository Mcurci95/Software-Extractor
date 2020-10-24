package com.sotwareextractor.cecs547.Model.unused;

import com.sotwareextractor.cecs547.Model.MType;

import javax.persistence.*;
import java.util.Collection;

public class _MClassMethodParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private int mOrder;
    @ManyToOne
    private MType mType;
    @ManyToMany(mappedBy = "mParameters")
    private Collection<_MClassMethod> mClassMethods;

    public _MClassMethodParameter() {}
    public _MClassMethodParameter(long id, String name, int mOrder, MType mType, Collection<_MClassMethod> mClassMethods) {
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
    public Collection<_MClassMethod> getmClassMethods() {
        return mClassMethods;
    }
    public void setmClassMethods(Collection<_MClassMethod> mClassMethods) {
        this.mClassMethods = mClassMethods;
    }
}
