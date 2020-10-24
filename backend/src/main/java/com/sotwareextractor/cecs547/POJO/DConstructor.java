package com.sotwareextractor.cecs547.POJO;

import java.util.ArrayList;
import java.util.List;

public class DConstructor {
    private List<DConstructorParameter> parameters = new ArrayList<>();
    private String name;
    private String type;

    public List<DConstructorParameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<DConstructorParameter> parameters) {
        this.parameters = parameters;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DConstructor{" +
                "parameters=" + parameters +
                ", name='" + name + '\'' +
                '}';
    }
}
