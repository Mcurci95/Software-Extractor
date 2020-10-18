package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;

public class FileListener extends JavaBaseListener {
    private String className;
    private String classModifier;
    private String parentClass;
    private String packageName;

    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        packageName = ctx.qualifiedName().Identifier().get(0).getText();
        System.out.println(packageName);
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        className = ctx.Identifier().getText();
        if (ctx.typeSpec() != null) {
            parentClass = ctx.typeSpec().getText();
        }

        System.out.println(className + ", parent: " + parentClass);
    }

    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        classModifier = ctx.classOrInterfaceModifier().get(0).getText();
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        ctx.classBodyDeclaration().forEach(body -> body.enterRule(new ClassBodyDeclarationListener()));
    }

    public String getPackageName() {
        return packageName;
    }
}
