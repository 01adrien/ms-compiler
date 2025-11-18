package mscompiler.expression.rvar;

import mscompiler.expression.Expression;
import mscompiler.interpreter.Env;
import mscompiler.value.NumberVal;
import mscompiler.value.Value;

public record MinusExp(Expression e) implements Expression {

    @Override
    public Value interpret(Env env) {
        return new NumberVal(-1 * e.interpret(env).as(Double.class));
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
