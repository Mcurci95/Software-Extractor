package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;

@Entity
public class MAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String accessName;

    public MAccess() {}
    public MAccess(String name) {
        this.accessName = name;
    }

    public String getName() {
        return accessName;
    }
    public void setName(String name) {
        this.accessName = name;
    }
}
