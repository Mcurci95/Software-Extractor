package com.sotwareextractor.cecs547.POJO;

import java.util.List;

public class DClassField {
    private List<String> modifiers;
    private String type;
    private String name;

    public List<String> getModifiers() {
        return modifiers;
    }
    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DClassField{" +
                "modifiers=" + modifiers +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
