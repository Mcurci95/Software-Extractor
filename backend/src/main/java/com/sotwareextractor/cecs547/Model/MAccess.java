package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
public class MAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String accessName;

    public MAccess() {}
    public MAccess(String accessName) {
        this.accessName = accessName;
    }

    public String getName() {
        return accessName;
    }
    public void setName(String name) {
        this.accessName = name;
    }

    @JsonIgnore
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getAccessName() {
        return accessName;
    }
    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }
}
