package com.sotwareextractor.cecs547.Parser;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.List;

public class CustomListener extends JavaBaseListener {
    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        System.out.println("Enter package");
        super.enterPackageDeclaration(ctx);
        System.out.println(ctx.annotation());
        System.out.println(ctx.qualifiedName().Identifier());
    }

    @Override
    public void exitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        System.out.println("Exit package");
        super.exitPackageDeclaration(ctx);
        System.out.println(ctx.annotation());
        System.out.println(ctx.qualifiedName().Identifier());
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        super.enterClassDeclaration(ctx);
        System.out.println(ctx.Identifier().getText());
    }

    @Override
    public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext ctx) {
        super.enterConstructorDeclaration(ctx);
        System.out.println(ctx.Identifier().getText());
    }

    @Override
    public void enterClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        super.enterClassBodyDeclaration(ctx);
        System.out.println(ctx.memberDeclaration().getText());
    }
}
