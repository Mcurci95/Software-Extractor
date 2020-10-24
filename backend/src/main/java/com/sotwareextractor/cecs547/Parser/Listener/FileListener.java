package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DClass;
import org.antlr.v4.runtime.RuleContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileListener extends JavaBaseListener {
    private DClass dClass = new DClass();
    public DClass getdClass() {
        return dClass;
    }

    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        if (ctx.classOrInterfaceModifier() != null) {
            List<String> modifiers = ctx.classOrInterfaceModifier().stream().map(RuleContext::getText).collect(Collectors.toList());
            dClass.setAccessLevel(modifiers);
        }
    }

    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        if (ctx.qualifiedName() != null) {
            String packageName = ctx.qualifiedName().Identifier().get(0).getText();
            dClass.setPackageName(packageName);
        }
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        dClass.setName(ctx.Identifier().getText());

        // Get class extends
        if (ctx.typeSpec() != null) {
            String parentClassName = ctx.typeSpec().getText();
            dClass.setParentClass(parentClassName);
        }

        // Get interface implementations
        if (ctx.typeList() != null) {
            for (var typeSpec : ctx.typeList().typeSpec()) {
                dClass.addInterface(typeSpec.getText());
            }
        }
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        ClassBodyDeclarationListener classBodyDeclarationListener = new ClassBodyDeclarationListener();
        ctx.classBodyDeclaration().forEach(body -> body.enterRule(classBodyDeclarationListener));
        dClass.setdClassFields(classBodyDeclarationListener.getClassFields());
        dClass.setdClassMethods(classBodyDeclarationListener.getClassMethods());
        dClass.setConstructors(classBodyDeclarationListener.getConstructors());
    }

    public void display() {
        System.out.println(dClass);
    }
}
