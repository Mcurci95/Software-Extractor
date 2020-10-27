package com.sotwareextractor.cecs547.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;


@Entity
public class MInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "implementInterfaces", cascade = CascadeType.ALL)
    private List<MClass> implementedClasses;

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

//    @JsonIgnore
    public List<MClass> getImplementedClasses() {
        return implementedClasses;
    }
    public void setImplementedClasses(List<MClass> implementedClasses) {
        this.implementedClasses = implementedClasses;
    }
}
