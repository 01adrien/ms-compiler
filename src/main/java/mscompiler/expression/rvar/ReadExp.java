package mscompiler.expression.rvar;

import java.util.Scanner;

import mscompiler.expression.Expression;
import mscompiler.interpreter.Env;
import mscompiler.value.NumberVal;
import mscompiler.value.Value;

public record ReadExp() implements Expression {

    @Override
    public Value interpret(Env env) {
        return new NumberVal(new Scanner(System.in).nextDouble());
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
