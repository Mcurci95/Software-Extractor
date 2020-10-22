package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DClassField;

import java.util.List;

public class ClassFieldListener  extends JavaBaseListener {
    private List<String> modifiers;
    private List<DClassField> dClassFields;

    public ClassFieldListener(List<String> modifiers, List<DClassField> dClassField) {
        this.modifiers = modifiers;
        this.dClassFields = dClassField;
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        if (ctx != null) {
            String type = ctx.typeSpec().getText();
            for (var variable : ctx.variableDeclarators().variableDeclarator()) {
                DClassField field = new DClassField();

                field.setType(type);
                field.setModifiers(modifiers);
                field.setName(variable.getText());
                dClassFields.add(field);
            }
        }
    }
}
