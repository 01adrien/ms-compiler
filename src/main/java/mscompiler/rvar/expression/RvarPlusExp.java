package mscompiler.rvar.expression;

import mscompiler.rvar.env.RvarEnv;

public record RvarPlusExp(RvarExpression a, RvarExpression b) implements RvarExpression {

    @Override
    public Integer interpret(RvarEnv env) {
        return a.interpret(env) + b.interpret(env);
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
