package com.sotwareextractor.cecs547.DAO;

public class DClassMethod {
    private String accessLevel;
    private String returnType;
    private String name;

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

    @Override
    public String toString() {
        return "DClassMethod{" +
                "returnType='" + returnType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
