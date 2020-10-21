package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;

@Entity
public class MType {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    public MType() {}

    public MType(String name) {
        this.name = name;
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
}
