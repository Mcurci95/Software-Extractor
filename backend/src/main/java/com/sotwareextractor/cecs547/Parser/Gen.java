package com.sotwareextractor.cecs547.Parser;

import com.softwareextractor.cecs547.Parser.JavaLexer;
import com.softwareextractor.cecs547.Parser.JavaParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.IOException;
import java.io.InputStream;

public class Gen {
    public static void main(String[] args) throws IOException {
        InputStream is = Gen.class.getClassLoader().getResourceAsStream(args[0]);
        ANTLRInputStream in = new ANTLRInputStream(is);
        JavaLexer lexer = new JavaLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        parser.compilationUnit();
    }
}
