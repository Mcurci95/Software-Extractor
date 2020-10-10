package com.sotwareextractor.cecs547.Model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class MAccess {
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
