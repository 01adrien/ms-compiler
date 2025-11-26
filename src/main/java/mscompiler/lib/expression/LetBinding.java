package mscompiler.lib.expression;

import mscompiler.lib.env.Env;

public class LetBinding<O extends Object, V extends Env<?, ?>> {

    protected final String id;
    protected final Expression<O, V> exp;

    public LetBinding(String id, Expression<O, V> exp) {
        this.id = id;
        this.exp = exp;
    }

    public String id() {
        return id;
    }

    public Expression<O, V> exp() {
        return exp;
    }

    @Override
    public String toString() {
        return String.format("[%s %s]", id, exp.toString());
    }
}
