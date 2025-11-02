package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.StringVal;
import mscompiler.value.Value;

public record StringExp(String value) implements Expression {

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Value interpret(Env env) {
        return new StringVal(value);
    }
}
