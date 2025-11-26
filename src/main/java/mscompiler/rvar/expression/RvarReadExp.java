package mscompiler.rvar.expression;

import java.util.Scanner;

import mscompiler.rvar.env.RvarEnv;

public record RvarReadExp() implements RvarExpression {

    @Override
    public Integer interpret(RvarEnv env) {
        return new Scanner(System.in).nextInt();
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
