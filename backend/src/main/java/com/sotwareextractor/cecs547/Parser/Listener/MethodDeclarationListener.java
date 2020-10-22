package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DClassMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodDeclarationListener extends JavaBaseListener {
    private List<String> modifiers;
    private List<DClassMethod> classMethods;
    private List<String[]> identifiers = new ArrayList<>();
    private Map<String, String> functionCalls;
    private String methodName;

    public MethodDeclarationListener(List<String> modifiers, List<DClassMethod> classMethods) {
        this.modifiers = modifiers;
        this.classMethods = classMethods;
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx != null) {
            String returnType = ctx.typeSpec() != null ? ctx.typeSpec().getText() : "void";
            methodName = ctx.Identifier().getText();
            DClassMethod newMethod = new DClassMethod();
            newMethod.setName(methodName);
            newMethod.setReturnType(returnType);
            newMethod.setModifiers(modifiers);

            if (ctx.methodBody() != null) {
                BlockListener blockListener = new BlockListener();
                ctx.methodBody().block().enterRule(blockListener);
                newMethod.setMethodStatements(blockListener.getMethodStatements());
                newMethod.setVariables(blockListener.getVariables());
                newMethod.setMethodCalls(blockListener.getMethodCalls());
            }

            classMethods.add(newMethod);
//            MethodParametersListener methodParametersListener = new MethodParametersListener();
//            if (ctx.formalParameters().formalParameterList() != null)
//                ctx.formalParameters().formalParameterList().enterRule(methodParametersListener);

//            methods.add(new String[] {returnType, methodName});
//            methodParams.put(methodName, methodParametersListener.getParameters());
//            mMethodService.add(methodName, modifier, className);
        }
    }

    public List<String[]> getIdentifiers() {
        return identifiers;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, String> getFunctionCalls() {
        return functionCalls;
    }
}
