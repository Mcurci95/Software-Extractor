package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DConstructor;
import com.sotwareextractor.cecs547.POJO.DConstructorParameter;

import java.util.ArrayList;
import java.util.List;

public class ConstructorListener extends JavaBaseListener {
    private DConstructor constructor = new DConstructor();
    private List<DConstructorParameter> constructorParameters = new ArrayList<>();

    @Override
    public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        constructor.setName(ctx.Identifier().getText());
        if (ctx.formalParameters().formalParameterList() != null) {
            for (var parameterCtx : ctx.formalParameters().formalParameterList().formalParameter()) {
                DConstructorParameter param = new DConstructorParameter();
                param.setName(parameterCtx.variableDeclaratorId().getText());
                param.setType(parameterCtx.typeSpec().getText());
                param.setOrder(constructorParameters.size());
                constructorParameters.add(param);
            }
        }

        constructor.setParameters(constructorParameters);
    }

    public DConstructor getConstructor() {
        return constructor;
    }
    public List<DConstructorParameter> getConstructorParameters() {
        return constructorParameters;
    }
}
