package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.Value;

public record VarExp(String id) implements Expression {

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Value interpret(Env env) {
        return env.lookup(id);
    }
}
