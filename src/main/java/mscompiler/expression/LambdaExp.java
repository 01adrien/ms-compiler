package mscompiler.expression;

import java.util.List;
import java.util.stream.Collectors;

import mscompiler.interpreter.Env;
import mscompiler.value.LambdaVal;
import mscompiler.value.Value;

public record LambdaExp(List<String> params, Expression body) implements Expression {

    public LambdaExp(List<String> params, Expression body) {
        this.params = List.copyOf(params);
        this.body = body;
    }

    @Override
    public String toString() {
        String paramStr = params.stream().collect(Collectors.joining(" "));
        return "(lambda (" + paramStr + ") " + body + ")";
    }

    @Override
    public Value interpret(Env env) {
        return new LambdaVal(params, body, env);
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
