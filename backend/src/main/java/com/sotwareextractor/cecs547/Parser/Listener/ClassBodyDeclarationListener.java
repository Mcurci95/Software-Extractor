package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.DAO.DClassField;
import com.sotwareextractor.cecs547.DAO.DClassMethod;

import java.util.ArrayList;
import java.util.List;

public class ClassBodyDeclarationListener extends JavaBaseListener {
    private List<DClassField> classFields = new ArrayList<>();
    private List<DClassMethod> classMethods = new ArrayList<>();

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
}
