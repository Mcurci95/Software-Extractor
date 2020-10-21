package com.sotwareextractor.cecs547.Parser;

import com.softwareextractor.cecs547.Parser.JavaLexer;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DClass;
import com.sotwareextractor.cecs547.Parser.Listener.FileListener;
import com.sotwareextractor.cecs547.Service.MClassService;
import com.sotwareextractor.cecs547.Service.MPackageService;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AntlrParser {
    private MPackageService mPackageService;
    private MClassService mClassService;

    @Autowired
    public AntlrParser(MPackageService mPackageService, MClassService mClassService) {
        this.mPackageService = mPackageService;
        this.mClassService = mClassService;
    }

//    public static void main(String[] args) throws IOException {
//        String[] files = {"Class1.java", "Class2.java", "Class3.java", "Program.java"};
//        for (String file : files) {
//            System.out.println("Parsing file: " + file);
//            JavaLexer lexer = new JavaLexer(CharStreams.fromFileName("/Users/chinhnguyen/Dropbox/School/CSULB/" +
//                    "Master/Fall 2020/CECS 547/Software-Extractor/backend/src/main/resources/OO_PIES1/" + file));
//            CommonTokenStream tokens = new CommonTokenStream(lexer);
//            JavaParser parser = new JavaParser(tokens);
//            JavaParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
//            ParseTreeWalker walker = new ParseTreeWalker();
//            FileListener fileListener = new FileListener();
//            walker.walk(fileListener, compilationUnitContext);
//
//            System.out.println("--------------------------------------------");
//        }
//    }

    public void parse(MultipartFile file) throws IOException {
        JavaLexer lexer = new JavaLexer(CharStreams.fromStream(file.getInputStream()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        JavaParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();
        FileListener fileListener = new FileListener();
        walker.walk(fileListener, compilationUnitContext);

        fileListener.display();
        saveToDb(fileListener);
    }

    public void saveToDb(FileListener fileListener) {
        DClass classData = fileListener.getdClass();
        mPackageService.getOrCreate(classData.getPackageName());
        mClassService.getOrCreate(classData);
    }
}
