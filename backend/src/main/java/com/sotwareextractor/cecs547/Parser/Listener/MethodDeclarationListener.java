package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.DAO.DClassMethod;

import java.util.List;

public class MethodDeclarationListener extends JavaBaseListener {
    private String modifier;
    private List<DClassMethod> classMethods;

    public MethodDeclarationListener(String modifier, List<DClassMethod> classMethods) {
        this.modifier = modifier;
        this.classMethods = classMethods;
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx != null) {
            String returnType = ctx.typeSpec() != null ? ctx.typeSpec().getText() : "void";
            String methodName = ctx.Identifier().getText();
            DClassMethod newMethod = new DClassMethod();
            newMethod.setName(methodName);
            newMethod.setReturnType(returnType);
            newMethod.setAccessLevel(modifier);

            classMethods.add(newMethod);
//            MethodParametersListener methodParametersListener = new MethodParametersListener();
//            if (ctx.formalParameters().formalParameterList() != null)
//                ctx.formalParameters().formalParameterList().enterRule(methodParametersListener);

//            methods.add(new String[] {returnType, methodName});
//            methodParams.put(methodName, methodParametersListener.getParameters());
//            mMethodService.add(methodName, modifier, className);
        }
    }
}
