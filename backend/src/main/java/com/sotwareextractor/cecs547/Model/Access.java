package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="access")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Access{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return Objects.equals(id, access.id) &&
                Objects.equals(name, access.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
