package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.Model.MAccess;
import com.sotwareextractor.cecs547.Model.MClass;
import com.sotwareextractor.cecs547.Model.MPackage;
import com.sotwareextractor.cecs547.Service.MAccessService;
import com.sotwareextractor.cecs547.Service.MClassService;
import com.sotwareextractor.cecs547.Service.MPackageService;
import com.sotwareextractor.cecs547.Service.MTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileListener extends JavaBaseListener {
    private String packageName;
    private String className;
    private String parentClass;
    private String modifier;

    private MPackageService mPackageService;
    private MClassService mClassService;
    private MAccessService mAccessService;

    @Autowired
    public void setmPackageService(MPackageService mPackageService) {
        this.mPackageService = mPackageService;
    }
    @Autowired
    public void setmClassService(MClassService mClassService) {
        this.mClassService = mClassService;
    }
    @Autowired
    public void setmAccessService(MAccessService mAccessService) {
        this.mAccessService = mAccessService;
    }

    @Override
    public void enterTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        modifier = ctx.classOrInterfaceModifier().get(0).getText();
        mAccessService.add(modifier);
    }

    @Override
    public void enterPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        packageName = ctx.qualifiedName().Identifier().get(0).getText();
        mPackageService.addPackage(packageName);
    }

    @Override
    public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        className = ctx.Identifier().getText();


        MClass classInstance = mClassService.add(className);

        classInstance.setmPackage(mPackageService.findByName(packageName));
        classInstance.setmAccess(mAccessService.findByName(modifier));

        if (ctx.typeSpec() != null) {
            parentClass = ctx.typeSpec().getText();
            MClass parentInstance = mClassService.add(parentClass);
            classInstance.setParent(parentInstance);
        }
    }

    @Override
    public void enterClassBody(JavaParser.ClassBodyContext ctx) {
        ctx.classBodyDeclaration().forEach(body -> body.enterRule(
                new ClassBodyDeclarationListener(mClassService.findByName(className))));
    }
}
