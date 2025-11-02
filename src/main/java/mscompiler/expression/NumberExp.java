package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.NumberVal;
import mscompiler.value.Value;

public record NumberExp(Double value) implements Expression {

    public NumberExp(String str) {
        this(Double.valueOf(str));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value interpret(Env env) {
        return new NumberVal(value);
    }
}
