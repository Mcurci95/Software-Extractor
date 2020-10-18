package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;

public class BlockListener extends JavaBaseListener {
    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        ctx.blockStatement().forEach(block ->
                block.localVariableDeclarationStatement().getText());
    }
}
