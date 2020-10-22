package com.sotwareextractor.cecs547.POJO;

import java.util.ArrayList;
import java.util.List;

public class DClassMethod {
    private String accessLevel;
    private String returnType;
    private String name;
    private List<DMethodStatement> methodStatements = new ArrayList<>();

    public String getReturnType() {
        return returnType;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    public List<DMethodStatement> getMethodStatements() {
        return methodStatements;
    }
    public void setMethodStatements(List<DMethodStatement> methodStatements) {
        this.methodStatements = methodStatements;
    }

    @Override
    public String toString() {
        return "DClassMethod{" +
                "accessLevel='" + accessLevel + '\'' +
                ", returnType='" + returnType + '\'' +
                ", name='" + name + '\'' +
                ", methodStatements=" + methodStatements +
                '}';
    }
}
