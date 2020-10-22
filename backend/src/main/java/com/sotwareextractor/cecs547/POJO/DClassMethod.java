package com.sotwareextractor.cecs547.POJO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DClassMethod {
    private List<String> modifiers;
    private String returnType;
    private String name;
    private List<DMethodStatement> methodStatements = new ArrayList<>();
    private Map<String, DVariable> variables = new HashMap<>();
    private List<String> methodCalls = new ArrayList<>();

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
    public List<String> getModifiers() {
        return modifiers;
    }
    public void setModifiers(List<String> modifiers) {
        this.modifiers = modifiers;
    }
    public List<DMethodStatement> getMethodStatements() {
        return methodStatements;
    }
    public void setMethodStatements(List<DMethodStatement> methodStatements) {
        this.methodStatements = methodStatements;
    }
    public Map<String, DVariable> getVariables() {
        return variables;
    }
    public void setVariables(Map<String, DVariable> variables) {
        this.variables = variables;
    }
    public List<String> getMethodCalls() {
        return methodCalls;
    }
    public void setMethodCalls(List<String> methodCalls) {
        this.methodCalls = methodCalls;
    }

    @Override
    public String toString() {
        return "DClassMethod{" +
                "modifiers=" + modifiers +
                ", returnType='" + returnType + '\'' +
                ", name='" + name + '\'' +
                ", methodStatements=" + methodStatements +
                ", variables=" + variables +
                ", methodCalls=" + methodCalls +
                '}';
    }
}
