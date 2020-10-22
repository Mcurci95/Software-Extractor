package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DClassField;
import com.sotwareextractor.cecs547.POJO.DClassMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassBodyDeclarationListener extends JavaBaseListener {
    private List<DClassField> classFields = new ArrayList<>();
    private List<DClassMethod> classMethods = new ArrayList<>();
    private Map<String, List<String[]>> cbIdentifiers = new HashMap<>();
    private Map<String, Map<String, String>> cbFunctionCalls = new HashMap<>();

    public List<DClassField> getClassFields() {
        return classFields;
    }
    public List<DClassMethod> getClassMethods() {
        return classMethods;
    }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        String modifier = null;
        if (ctx.modifier().size() != 0) {
            modifier = ctx.modifier().get(0).getText();
        }

        ClassFieldListener classFieldListener = new ClassFieldListener(modifier, classFields);
        if (ctx.memberDeclaration().fieldDeclaration() != null) {
            ctx.memberDeclaration().fieldDeclaration().enterRule(classFieldListener);
        }

        MethodDeclarationListener methodDeclarationListener = new MethodDeclarationListener(modifier, classMethods);
        if (ctx.memberDeclaration().methodDeclaration() != null) {
            ctx.memberDeclaration().methodDeclaration().enterRule(methodDeclarationListener);
        }
        if (methodDeclarationListener.getMethodName() != null) {
            cbIdentifiers.put(methodDeclarationListener.getMethodName(), methodDeclarationListener.getIdentifiers());
            cbFunctionCalls.put(methodDeclarationListener.getMethodName(), methodDeclarationListener.getFunctionCalls());
        }
//        JavaParser.ConstructorDeclarationContext constructorDeclarationContext = ctx.memberDeclaration().constructorDeclaration();
//        if (constructorDeclarationContext != null) {
//            MethodParametersListener constructorParams = new MethodParametersListener();
//            if (constructorDeclarationContext.formalParameters().formalParameterList() != null)
//                constructorDeclarationContext.formalParameters().formalParameterList().enterRule(constructorParams);
//            constructorParamList = constructorParams.parameters;
//        }

        MethodDeclarationListener constructorListener = new MethodDeclarationListener(modifier, classMethods);
        if (ctx.memberDeclaration().constructorDeclaration() != null) {
            ctx.memberDeclaration().constructorDeclaration().enterRule(constructorListener);
        }
    }

    public Map<String, List<String[]>> getCbIdentifiers() {
        return cbIdentifiers;
    }

    public Map<String, Map<String, String>> getCbFunctionCalls() {
        return cbFunctionCalls;
    }
}
