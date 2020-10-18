package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class MPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "mPackage")
    private Collection<MClass> MClasses;

    public MPackage() {}
    public MPackage(Long id, String name, Collection<MClass> MClasses) {
        this.id = id;
        this.name = name;
        this.MClasses = MClasses;
    }
    public MPackage(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Collection<MClass> getMClasses() {
        return MClasses;
    }
    public void setMClasses(Collection<MClass> MClasses) {
        this.MClasses = MClasses;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
