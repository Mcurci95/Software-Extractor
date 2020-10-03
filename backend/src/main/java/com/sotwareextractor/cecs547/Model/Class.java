package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    private Access access;
    @ManyToOne
    private Package classPackage; // package is a reserved word

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Access getAccess() {
        return access;
    }
    public void setAccess(Access access) {
        this.access = access;
    }
    public Package getClassPackage() {
        return classPackage;
    }
    public void setClassPackage(Package classPackage) {
        this.classPackage = classPackage;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", access=" + access +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id) &&
                Objects.equals(name, aClass.name) &&
                Objects.equals(access, aClass.access);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, access);
    }
}
