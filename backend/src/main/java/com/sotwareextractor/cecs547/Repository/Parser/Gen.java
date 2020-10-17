package com.sotwareextractor.cecs547.Repository.Parser;

import com.softwareextractor.cecs547.Parser.JavaLexer;
import com.softwareextractor.cecs547.Parser.JavaParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Gen {
    public static void main(String[] args) throws IOException {
        JavaLexer lexer = new JavaLexer(CharStreams.fromFileName("src/main/resources/OO_PIES1/Class1.java"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        JavaParser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new CustomListener(), compilationUnitContext);
    }
}
