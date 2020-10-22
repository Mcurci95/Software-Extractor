package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DMethodStatement;

import java.util.*;

public class BlockListener extends JavaBaseListener {
    private int sequence = 0;
    List<DMethodStatement> methodStatements = new ArrayList<>();

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        for (var blockStatement : ctx.blockStatement()) {
            DMethodStatement dStatement = new DMethodStatement();
            dStatement.setStatement(blockStatement.getText());
            dStatement.setSequence(sequence++);

            List<String> identifiers = new ArrayList<>();


            Queue<JavaParser.ExpressionContext> queue;
            Set<JavaParser.ExpressionContext> seenExpression;

            if (blockStatement.statement() != null && blockStatement.statement().statementExpression() != null
                    && blockStatement.statement().statementExpression().expression() != null) {
                queue = new LinkedList<>();
                seenExpression = new HashSet<>();
                queue.add(blockStatement.statement().statementExpression().expression());
                queue.addAll(blockStatement.statement().statementExpression().expression().expression());
                while(queue.size() != 0) {
                    JavaParser.ExpressionContext eCtx = queue.poll();
                    if (!seenExpression.contains(eCtx)) {
                        seenExpression.add(eCtx);
                        if (eCtx.DOT() != null) {
                            identifiers.add(eCtx.getText());
                        }
                        else if (eCtx.primary() != null && eCtx.primary().Identifier() != null) {
                            identifiers.add(eCtx.primary().Identifier().getText());
                        }
                        if (eCtx.expression().size() != 0) {
                            for (var expression : eCtx.expression()) {
                                if (!seenExpression.contains(expression)) {
                                    queue.add(expression);
                                }
                            }
                        }
                    }
                }
            }
            if (blockStatement.statement() != null && blockStatement.statement().expression() != null) {
                queue = new LinkedList<>(blockStatement.statement().expression());
                seenExpression = new HashSet<>();
                while(queue.size() != 0) {
                    JavaParser.ExpressionContext eCtx = queue.poll();
                    if (!seenExpression.contains(eCtx)) {
                        seenExpression.add(eCtx);
                        if (eCtx.DOT() != null) {
                            identifiers.add(eCtx.getText());
                        }
                        else if (eCtx.primary() != null && eCtx.primary().Identifier() != null) {
                            identifiers.add(eCtx.primary().Identifier().getText());
                        }
                        if (eCtx.expression().size() != 0) {
                            for (var expression : eCtx.expression()) {
                                if (!seenExpression.contains(expression)) {
                                    queue.add(expression);
                                }
                            }
                        }
                    }
                }
            }
            if (blockStatement.localVariableDeclarationStatement() != null &&
            blockStatement.localVariableDeclarationStatement().localVariableDeclaration() != null
            && blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators() != null
            && blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators().variableDeclarator().size() != 0) {
                var variableDeclaratior = blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators().variableDeclarator();
                for (var varId : variableDeclaratior) {
                    if (varId.variableDeclaratorId() != null && varId.variableDeclaratorId().Identifier() != null) {
                        identifiers.add(varId.variableDeclaratorId().Identifier().getText());
                    }
                    if (varId.variableInitializer() != null) {

                    }
                }
            }

            dStatement.setIdentifiers(identifiers);
            methodStatements.add(dStatement);
        }
    }

    public List<DMethodStatement> getMethodStatements() {
        return methodStatements;
    }

    private void handleExpression() {
        
    }
}
