package mscompiler.expression.rvar;

import mscompiler.expression.Expression;
import mscompiler.interpreter.Env;
import mscompiler.value.NumberVal;
import mscompiler.value.Value;

public record PlusExp(Expression a, Expression b) implements Expression {

    @Override
    public Value interpret(Env env) {
        return new NumberVal(
                a.interpret(env).as(Double.class) +
                        b.interpret(env).as(Double.class));
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
