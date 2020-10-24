package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
public class MVariable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;


    @ManyToOne(cascade = CascadeType.ALL)
    private MType mReturnType;

    private String value;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private MMethodBody mMethodBody;


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

    @JsonIgnore
    public MType getmReturnType() {
        return mReturnType;
    }
    public void setmReturnType(MType mReturnType) {
        this.mReturnType = mReturnType;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public MMethodBody getmMethodBody() {
        return mMethodBody;
    }
    public void setmMethodBody(MMethodBody mMethodBody) {
        this.mMethodBody = mMethodBody;
    }
}
