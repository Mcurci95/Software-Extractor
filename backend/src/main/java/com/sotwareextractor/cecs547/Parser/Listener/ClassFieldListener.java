package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;

import java.util.ArrayList;
import java.util.List;

public class ClassFieldListener  extends JavaBaseListener {
    List<String[]> fields = new ArrayList<>();
    private String modifier;

    public ClassFieldListener(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public void enterFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        if (ctx != null) {
            if (modifier != null)
                for (var variable : ctx.variableDeclarators().variableDeclarator())
                    fields.add(new String[] {
                            modifier,
                            ctx.typeSpec().getText(),
                            variable.getText()
                    });
            else {
                fields.add(new String[] {
                        ctx.typeSpec().getText(),
                        ctx.variableDeclarators().getText()
                });
            }
        }
    }
}
