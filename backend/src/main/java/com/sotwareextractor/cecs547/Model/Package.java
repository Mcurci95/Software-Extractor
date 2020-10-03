package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(
            mappedBy = "package",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Class> classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return Objects.equals(id, aPackage.id) &&
                Objects.equals(name, aPackage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
