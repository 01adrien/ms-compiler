package mscompiler.expression;

import mscompiler.interpreter.Env;
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

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
