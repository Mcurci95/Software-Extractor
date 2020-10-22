package com.sotwareextractor.cecs547.POJO;

import java.util.*;

public class DMethodStatement {
    private String statement;
    private List<String> identifiers;
    private int sequence;
    private Map<String, String> objectMethodCall = new HashMap<>();

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        int returnIndex = statement.indexOf("return");
        if (returnIndex != -1) {
            statement = statement.substring(0, 6) + " " + statement.substring(6);
        }
        this.statement = statement;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
        findMethodCalls();
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void findMethodCalls() {
        for (String identifier : identifiers) {
            int periodIdx = identifier.indexOf(".");
            if (periodIdx != -1) {
                String[] pair = identifier.split("\\.");
                objectMethodCall.put(pair[0], pair[1]);
            }
        }
    }

    @Override
    public String toString() {
        return "DMethodStatement{" +
                "statement='" + statement + '\'' +
                ", identifiers=" + identifiers +
                ", sequence=" + sequence +
                ", objectMethodCall=" + objectMethodCall +
                '}';
    }
}
