package mscompiler.rvar.expression;

import mscompiler.rvar.env.RvarEnv;

public record RvarNumExp(Integer n) implements RvarExpression {

    public RvarNumExp(String str) {
        this(Integer.valueOf(str));
    }

    @Override
    public String toString() {
        return n.toString();
    }

    @Override
    public Integer interpret(RvarEnv env) {
        return n;
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
