package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.Model.MClass;
import org.springframework.stereotype.Component;

@Component
public class ClassBodyDeclarationListener extends JavaBaseListener {
    String[] constructorParamList;
    private MClass classInstance;

    public ClassBodyDeclarationListener() {
    }

    public ClassBodyDeclarationListener(MClass classInstance) {
        this.classInstance = classInstance;
    }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        String modifier = null;
        if (ctx.modifier().size() != 0) {
            modifier = ctx.modifier().get(0).getText();
        }
        ClassFieldListener classFieldListener = new ClassFieldListener(modifier);
        if (ctx.memberDeclaration().fieldDeclaration() != null) {
            ctx.memberDeclaration().fieldDeclaration().enterRule(classFieldListener);
        }

        MethodDeclarationListener methodDeclarationListener = new MethodDeclarationListener(modifier, classInstance.getName());
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

        MethodDeclarationListener constructorListener = new MethodDeclarationListener(modifier, classInstance.getName());
        if (ctx.memberDeclaration().constructorDeclaration() != null) {
            ctx.memberDeclaration().constructorDeclaration().enterRule(constructorListener);
        }
    }
}
