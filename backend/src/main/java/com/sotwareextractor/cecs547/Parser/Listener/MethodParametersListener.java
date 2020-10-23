package com.sotwareextractor.cecs547.Parser.Listener;


import com.softwareextractor.cecs547.Parser.JavaBaseListener;
import com.softwareextractor.cecs547.Parser.JavaParser;
import com.sotwareextractor.cecs547.POJO.DMethodParam;

import java.util.ArrayList;
import java.util.List;

public class MethodParametersListener extends JavaBaseListener {
    List<DMethodParam> params = new ArrayList<>();

    @Override
    public void enterFormalParameterList(JavaParser.FormalParameterListContext ctx) {
        for (int i = 0; i < ctx.formalParameter().size(); i++) {
            JavaParser.FormalParameterContext parameter = ctx.formalParameter(i);
            String type = parameter.typeSpec().getText();
            String name = parameter.variableDeclaratorId().Identifier().getText();

            DMethodParam param = new DMethodParam();
            param.setType(type);
            param.setName(name);
            param.setOrder(i);
            params.add(param);
        }
    }

    public List<DMethodParam> getParams() {
        return params;
    }
}
