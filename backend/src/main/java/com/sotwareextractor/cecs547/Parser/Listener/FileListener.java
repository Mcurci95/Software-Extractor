package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.DAO.DClass;
import org.springframework.stereotype.Component;

@Component
public class FileListener extends JavaBaseListener {
    private DClass dClass = new DClass();

    public DClass getdClass() {
        return dClass;
    }

    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        if (ctx.classOrInterfaceModifier() != null) {
            String modifier = ctx.classOrInterfaceModifier().get(0).getText();
            dClass.setAccessLevel(modifier);
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

        if (ctx.typeSpec() != null) {
            String parentClassName = ctx.typeSpec().getText();
            dClass.setParentClass(parentClassName);
        }
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        ClassBodyDeclarationListener classBodyDeclarationListener = new ClassBodyDeclarationListener();
        ctx.classBodyDeclaration().forEach(body -> body.enterRule(classBodyDeclarationListener));
        dClass.setdClassFields(classBodyDeclarationListener.getClassFields());
        dClass.setdClassMethods(classBodyDeclarationListener.getClassMethods());
    }

    public void display() {
        System.out.println(dClass);
    }
}
