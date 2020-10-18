package com.sotwareextractor.cecs547.Parser;

import com.softwareextractor.cecs547.Parser.JavaLexer;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.Parser.Listener.FileListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Gen {
    public static void main(String[] args) throws IOException {
        String[] files = {"Class1.java", "Class2.java", "Class3.java", "Program.java"};
        for (String file : files) {
            System.out.println("Parsing file: " + file);
            JavaLexer lexer = new JavaLexer(CharStreams.fromFileName("/Users/chinhnguyen/Dropbox/School/CSULB/" +
                    "Master/Fall 2020/CECS 547/Software-Extractor/backend/src/main/resources/OO_PIES1/" + file));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);
            JavaParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
            ParseTreeWalker walker = new ParseTreeWalker();
            FileListener fileListener = new FileListener();
            walker.walk(fileListener, compilationUnitContext);

            System.out.println("--------------------------------------------");
        }
    }
}
