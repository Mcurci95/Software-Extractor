package com.sotwareextractor.cecs547.POJO;

import java.util.Arrays;

public class DVariable {
    public static final String METHOD_INVOKE = "method_invocation";
    public static final String OBJECT_CREATION = "object";
    public static final String VALUE = "value";

    private String type;
    private String name;
    private String[] value;

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

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DVariable{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
