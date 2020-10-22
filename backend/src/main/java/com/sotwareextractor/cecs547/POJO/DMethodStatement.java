package com.sotwareextractor.cecs547.POJO;

import java.util.List;

public class DMethodStatement {
    private String statement;
    private List<String> identifiers;

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
    }

    @Override
    public String toString() {
        return "DMethodStatement{" +
                "statement='" + statement + '\'' +
                ", identifiers=" + identifiers +
                '}';
    }
}
