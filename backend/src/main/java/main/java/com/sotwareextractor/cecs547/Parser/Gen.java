package main.java.com.sotwareextractor.cecs547.Parser;

import main.java.com.sotwareextractor.cecs547.Parser.gen.JavaLexer;
import main.java.com.sotwareextractor.cecs547.Parser.gen.JavaParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.io.InputStream;

public class Gen {
    public static void main(String[] args) throws IOException {
        InputStream is = Gen.class.getClassLoader().getResourceAsStream("OO_PIES1/Program.java");
        ANTLRInputStream in = new ANTLRInputStream(is);
        JavaLexer lexer = new JavaLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        parser.compilationUnit();
    }
}
