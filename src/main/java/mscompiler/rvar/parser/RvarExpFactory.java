package mscompiler.rvar.parser;

import java.util.List;

import mscompiler.lib.env.Env;
import mscompiler.lib.expression.Expression;
import mscompiler.lib.expression.ExpressionFactory;
import mscompiler.lib.expression.LetBinding;

public interface RvarExpFactory<O, E extends Expression<O, V>, V extends Env<?, ?>, B extends LetBinding<O, V>>
        extends ExpressionFactory<E, V> {
    E makeNumber(int n);

    E makeVar(String name);

    E makeLet(List<B> envBindings, E body);

    B makeBinding(String id, Expression<O, V> exp);

    E makePlus(E a, E b);

    E makeNeg(E a);

    E makeRead();
}