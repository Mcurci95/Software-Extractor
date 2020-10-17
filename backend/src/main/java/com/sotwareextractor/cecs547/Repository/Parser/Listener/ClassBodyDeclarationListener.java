package com.sotwareextractor.cecs547.Repository.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;

import java.util.*;

public class ClassBodyDeclarationListener extends JavaBaseListener {
    List<String[]> fields = new ArrayList<>();
    List<String[]> methods = new ArrayList<>();
    Map<String, String[]> methodParams = new HashMap<>();
    String[] constructorParamList;

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        JavaParser.FieldDeclarationContext fieldCtx = ctx.memberDeclaration().fieldDeclaration();
        if (fieldCtx != null) {
            if (ctx.modifier().size() != 0)
                for (var variable : fieldCtx.variableDeclarators().variableDeclarator())
                    fields.add(new String[] {
                            ctx.modifier().get(0).getText(),
                            fieldCtx.typeSpec().getText(),
                            variable.getText()
                    });
            else {
                fields.add(new String[] {
                        fieldCtx.typeSpec().getText(),
                        fieldCtx.variableDeclarators().getText()
                });
            }
        }

        JavaParser.MethodDeclarationContext methodCtx = ctx.memberDeclaration().methodDeclaration();
        if (methodCtx != null) {
            String returnType = methodCtx.typeSpec() != null ? methodCtx.typeSpec().getText() : "void";
            String methodName = methodCtx.Identifier().getText();
            MethodParametersListener methodParametersListener = new MethodParametersListener();
            if (methodCtx.formalParameters().formalParameterList() != null)
                methodCtx.formalParameters().formalParameterList().enterRule(methodParametersListener);

            methods.add(new String[] {returnType, methodName});
            methodParams.put(methodName, methodParametersListener.getParameters());
        }

        JavaParser.ConstructorDeclarationContext constructorDeclarationContext = ctx.memberDeclaration().constructorDeclaration();
        if (constructorDeclarationContext != null) {
            MethodParametersListener constructorParams = new MethodParametersListener();
            if (constructorDeclarationContext.formalParameters().formalParameterList() != null)
                constructorDeclarationContext.formalParameters().formalParameterList().enterRule(constructorParams);
            constructorParamList = constructorParams.parameters;
        }

        display();
    }

    public void display() {
        displayField();
        displayConstructor();
        displayMethods();
    }

    public void displayField() {
        if (fields.size() != 0) {
            System.out.println("Data Memebers");
            fields.forEach(field -> System.out.println(Arrays.toString(field)));
            System.out.println("---------");
        }
    }

    public void displayConstructor() {
        if (constructorParamList != null && constructorParamList.length != 0) {
            System.out.print("Constructor: ");
            System.out.println(Arrays.toString(constructorParamList));
            System.out.println("---------");
        }
    }

    public void displayMethods() {
        if (methods.size() != 0) {
            System.out.println("Methods");
            for (String[] methodInfo : methods) {
                String name = methodInfo[methodInfo.length - 1];
                System.out.println(name + ": " + Arrays.toString(methodInfo) + ", params: " + Arrays.toString(methodParams.get(name)));
            }
            System.out.println("---------");
        }

    }
}
