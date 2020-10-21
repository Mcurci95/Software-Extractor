package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.DAO.DClass;
import com.sotwareextractor.cecs547.DAO.DClassField;

import java.util.ArrayList;
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
                field.setModifier(modifier);
                field.setName(variable.getText());
                dClassFields.add(field);
            }
//            if (modifier != null)
//                for (var variable : ctx.variableDeclarators().variableDeclarator())
//                    fields.add(new String[] {
//                            modifier,
//                            ctx.typeSpec().getText(),
//                            variable.getText()
//                    });
//            else {
//                fields.add(new String[] {
//                        ctx.typeSpec().getText(),
//                        ctx.variableDeclarators().getText()
//                });
//            }
        }
    }
}
