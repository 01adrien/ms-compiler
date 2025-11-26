package mscompiler.rvar.expression;

import mscompiler.rvar.env.RvarEnv;

public record RVarVarExp(String id) implements RvarExpression {

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Integer interpret(RvarEnv env) {
        return env.lookup(id);
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
