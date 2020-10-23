package com.sotwareextractor.cecs547.POJO;

import java.util.ArrayList;
import java.util.List;

public class DClass {
    private String name;
    private List<String> accessLevel;
    private String packageName;
    private String parentClass;
    private List<DClassField> dClassFields = new ArrayList<>();
    private List<DClassMethod> dClassMethods = new ArrayList<>();
    private List<String> implementInterfaces = new ArrayList<>();

    public List<DClassMethod> getdClassMethods() {
        return dClassMethods;
    }

    public void setdClassMethods(List<DClassMethod> dClassMethods) {
        this.dClassMethods = dClassMethods;
    }

    public List<DClassField> getFields() {
        return dClassFields;
    }

    public void setdClassFields(List<DClassField> dClassFields) {
        this.dClassFields = dClassFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(List<String> accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public List<DClassField> getdClassFields() {
        return dClassFields;
    }

    public List<String> getImplementInterfaces() {
        return implementInterfaces;
    }

    public void setImplementInterfaces(List<String> implementInterfaces) {
        this.implementInterfaces = implementInterfaces;
    }

    public void addInterface(String name) {
        this.implementInterfaces.add(name);
    }

    @Override
    public String toString() {
        return "DClass{" +
                "name='" + name + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", packageName='" + packageName + '\'' +
                ", parentClass='" + parentClass + '\'' +
                ", dClassFields=" + dClassFields +
                ", dClassMethods=" + dClassMethods +
                ", implementInterfaces=" + implementInterfaces +
                '}';
    }
}
