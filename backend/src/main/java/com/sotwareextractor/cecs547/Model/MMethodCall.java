package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;


@Entity
public class MMethodCall {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    private String owner;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private MMethodBody mMethodBody;


//    @JsonIgnore
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

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

//    @JsonIgnore
    public MMethodBody getmMethodBody() {
        return mMethodBody;
    }
    public void setmMethodBody(MMethodBody mMethodBody) {
        this.mMethodBody = mMethodBody;
    }

    public void parseCall(String str) {
        int pos = str.indexOf(".");

        if (str.contains("System")) {
            int secondPeriod = str.indexOf(".", pos + 1);
            name = str.substring(secondPeriod+1);
            owner = str.substring(0, secondPeriod);
        } else {
            if (pos == -1) {
                name = str;
            } else {
                owner = str.substring(0, pos);
                name = str.substring(pos + 1);
            }
        }
    }
}
