package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;

@Entity
public class MConstructorParameter {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private int mOrder;
    @ManyToOne(cascade = CascadeType.ALL)
    private MType mType;
    @ManyToOne(cascade = CascadeType.ALL)
    private MConstructor mConstructor;

    public MConstructorParameter() {
    }
    public MConstructorParameter(String name, int mOrder, MType mType, MConstructor mConstructor) {
        this.name = name;
        this.mOrder = mOrder;
        this.mType = mType;
        this.mConstructor = mConstructor;
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
    public MConstructor getmConstructor() {
        return mConstructor;
    }
    public void setmConstructor(MConstructor mConstructor) {
        this.mConstructor = mConstructor;
    }
}
