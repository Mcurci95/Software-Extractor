package com.sotwareextractor.cecs547.Parser.Listener;


import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.Model.MClass;

public class MethodParametersListener extends JavaBaseListener {
    String[] parameters;


    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        for (int i = 0; i < ctx.formalParameter().size(); i++) {
            JavaParser.FormalParameterContext parameter = ctx.formalParameter(i);
            String type = parameter.typeSpec().getText();
            String name = parameter.variableDeclaratorId().Identifier().getText();
            parameters = new String[] {String.valueOf(i), type, name};
        }
    }

    public String[] getParameters() {
        return parameters;
    }
}
