package mscompiler.rvar.expression;

import mscompiler.rvar.env.RvarEnv;

public record RvarNegExp(RvarExpression e) implements RvarExpression {

    @Override
    public Integer interpret(RvarEnv env) {
        return -1 * e.interpret(env);
    }

    @Override
    public Void generateAsm() {
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
