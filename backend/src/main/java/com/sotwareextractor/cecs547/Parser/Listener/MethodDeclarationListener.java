package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.Service.MMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MethodDeclarationListener extends JavaBaseListener {
    private String className;
    private String modifier;

    private MMethodService mMethodService;
    @Autowired
    public void setmMethodService(MMethodService mMethodService) {
        this.mMethodService = mMethodService;
    }

    public MethodDeclarationListener() {
    }

    public MethodDeclarationListener(String modifier, String className) {
        this.modifier = modifier;
        this.className = className;
    }

    @Override
    public void enterMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        if (ctx != null) {
            String returnType = ctx.typeSpec() != null ? ctx.typeSpec().getText() : "void";
            String methodName = ctx.Identifier().getText();
            MethodParametersListener methodParametersListener = new MethodParametersListener();
            if (ctx.formalParameters().formalParameterList() != null)
                ctx.formalParameters().formalParameterList().enterRule(methodParametersListener);

//            methods.add(new String[] {returnType, methodName});
//            methodParams.put(methodName, methodParametersListener.getParameters());
//            mMethodService.add(methodName, modifier, className);
        }
    }
}
