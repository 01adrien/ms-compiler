package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.Value;

public record DefineExp(String id, Expression exp) implements Expression {

    @Override
    public String toString() {
        return "(define " + id + " " + exp.toString() + ")";
    }

    @Override
    public Value interpret(Env env) {
        Value value = exp.interpret(env);
        env.define(id, value);
        return value;
    }

}