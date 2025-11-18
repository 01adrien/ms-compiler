package mscompiler.expression;

import mscompiler.interpreter.Env;
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

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
