package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.DAO.DClassField;

import java.util.List;

public class ClassFieldListener  extends JavaBaseListener {
    private String modifier;
    private List<DClassField> dClassFields;

    public ClassFieldListener(String modifier, List<DClassField> dClassField) {
        this.modifier = modifier;
        this.dClassFields = dClassField;
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        if (ctx != null) {
            String type = ctx.typeSpec().getText();
            for (var variable : ctx.variableDeclarators().variableDeclarator()) {
                DClassField field = new DClassField();

                field.setType(type);
                field.setAccessLevel(modifier);
                field.setName(variable.getText());
                dClassFields.add(field);
            }
        }
    }
}
