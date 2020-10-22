package com.sotwareextractor.cecs547.Parser.Listener;

import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DMethodStatement;
import com.sotwareextractor.cecs547.POJO.DVariable;

import java.util.*;

public class BlockListener extends JavaBaseListener {
    private int sequence = 0;
    List<DMethodStatement> methodStatements = new ArrayList<>();
    Map<String, DVariable> variables = new HashMap<>();
    List<String> methodCalls = new ArrayList<>();

    @Override
    public void enterBlock(JavaParser.BlockContext ctx) {
        for (var blockStatement : ctx.blockStatement()) {
            DMethodStatement dStatement = new DMethodStatement();
            dStatement.setStatement(blockStatement.getText());
            dStatement.setSequence(sequence++);

            List<String> identifiers = new ArrayList<>();

            if (blockStatement.statement() != null && blockStatement.statement().statementExpression() != null
                    && blockStatement.statement().statementExpression().expression() != null) {
                handleExpression(blockStatement.statement().statementExpression().expression(), identifiers);
            }

            if (blockStatement.statement() != null && blockStatement.statement().expression() != null) {
                for (var expression : blockStatement.statement().expression()) {
                    handleExpression(expression, identifiers);
                }
            }

//            if (blockStatement.localVariableDeclarationStatement() != null &&
//            blockStatement.localVariableDeclarationStatement().localVariableDeclaration() != null
//            && blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators() != null
//            && blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators().variableDeclarator().size() != 0) {
//                var variableDeclaratior = blockStatement.localVariableDeclarationStatement().localVariableDeclaration().variableDeclarators().variableDeclarator();
//                for (var varId : variableDeclaratior) {
//                    if (varId.variableDeclaratorId() != null && varId.variableDeclaratorId().Identifier() != null) {
//                        identifiers.add(varId.variableDeclaratorId().Identifier().getText());
//                    }
//                    if (varId.variableInitializer() != null) {
//                        handleExpression(varId.variableInitializer().expression(), identifiers);
//                    }
//                }
//            }

            if (blockStatement.localVariableDeclarationStatement() != null &&
                    blockStatement.localVariableDeclarationStatement().localVariableDeclaration() != null) {
                var localVariableDeclaration = blockStatement.localVariableDeclarationStatement().localVariableDeclaration();
                String type = localVariableDeclaration.typeSpec().getText();
                for (var variableDeclarator : localVariableDeclaration.variableDeclarators().variableDeclarator()) {
                    DVariable variable = new DVariable();
                    variable.setType(type);
                    variable.setName(variableDeclarator.variableDeclaratorId().getText());
                    if (variableDeclarator.variableInitializer() != null && variableDeclarator.variableInitializer().expression() != null) {
                        var expression = variableDeclarator.variableInitializer().expression();
                        if (expression.NEW() != null) {
                            variable.setValue(new String[]{DVariable.OBJECT_CREATION, expression.creator().getText()});
                        } else if (expression.DOT() != null) {
                            handleExpression(expression, variable);
                        }
                    }

                    variables.put(variable.getName(), variable);
                }
            }

            dStatement.setIdentifiers(identifiers);
            methodStatements.add(dStatement);
        }
    }

    public List<DMethodStatement> getMethodStatements() {
        return methodStatements;
    }

    public Map<String, DVariable> getVariables() {
        return variables;
    }

    public List<String> getMethodCalls() {
        return methodCalls;
    }

    private void handleExpression(JavaParser.ExpressionContext expression, List<String> identifiers) {
        Queue<JavaParser.ExpressionContext> queue = new LinkedList<>();
        Set<JavaParser.ExpressionContext> seenExpression;
        seenExpression = new HashSet<>();

        queue.add(expression);
        while (queue.size() != 0) {
            JavaParser.ExpressionContext eCtx = queue.poll();
            if (!seenExpression.contains(eCtx)) {
                seenExpression.add(eCtx);
                if (eCtx.DOT() != null) {
                    methodCalls.add(eCtx.getText());
                } else if (eCtx.creator() != null) {
                    identifiers.add(eCtx.creator().getText());
                }
                else if (eCtx.primary() != null && eCtx.primary().Identifier() != null) {
                    identifiers.add(eCtx.primary().Identifier().getText());
                }
                if (eCtx.expression().size() != 0) {
                    for (var anExpression : eCtx.expression()) {
                        if (!seenExpression.contains(anExpression)) {
                            queue.add(anExpression);
                        }
                    }
                }
            }
        }
    }

    private void handleExpression(JavaParser.ExpressionContext expression, DVariable variable) {
        Queue<JavaParser.ExpressionContext> queue = new LinkedList<>();
        Set<JavaParser.ExpressionContext> seenExpression;
        seenExpression = new HashSet<>();

        queue.add(expression);
        while (queue.size() != 0) {
            JavaParser.ExpressionContext eCtx = queue.poll();
            if (!seenExpression.contains(eCtx)) {
                seenExpression.add(eCtx);
                if (eCtx.DOT() != null) {
                    variable.setValue(new String[]{DVariable.METHOD_INVOKE, eCtx.getText()});
                    if (eCtx.expression().size() != 0) {
                        for (var anExpression : eCtx.expression()) {
                            if (!seenExpression.contains(anExpression)) {
                                queue.add(anExpression);
                            }
                        }
                    }
                }
            }
        }
    }
}