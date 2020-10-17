package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class MInstanceParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private int order;

    @ManyToOne
    private MType type;

    @ManyToMany(mappedBy = "parameters")
    private Collection<MInstanceMethod> instanceMethods;

    public MInstanceParameter(){}
    public MInstanceParameter(long id, String name, int order, MType type, Collection<MInstanceMethod> instanceMethods) {
        Id = id;
        this.name = name;
        this.order = order;
        this.type = type;
        this.instanceMethods = instanceMethods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MType getType() {
        return type;
    }

    public void setType(MType type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
