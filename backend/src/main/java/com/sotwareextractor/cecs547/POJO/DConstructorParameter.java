package com.sotwareextractor.cecs547.POJO;

public class DConstructorParameter {
    private String type;
    private String name;
    private int order;

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
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "DConstructorParameter{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
