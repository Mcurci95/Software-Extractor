package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;


@Entity
public class MConstructor {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    @ManyToOne(cascade = CascadeType.ALL)
    private MType type;
    private String name;


    @OneToMany(mappedBy = "mConstructor", cascade = CascadeType.ALL)
    private List<MConstructorParameter> parameters;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private MClass mClass;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public MType getType() {
        return type;
    }
    public void setType(MType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<MConstructorParameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<MConstructorParameter> parameters) {
        this.parameters = parameters;
    }
    public MClass getmClass() {
        return mClass;
    }
    public void setmClass(MClass mClass) {
        this.mClass = mClass;
    }
}
