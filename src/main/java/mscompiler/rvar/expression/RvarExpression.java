package mscompiler.rvar.expression;

import mscompiler.lib.env.Env;
import mscompiler.lib.expression.Expression;
import mscompiler.rvar.interpreter.RvarVisitor;
import mscompiler.rvar.value.RvarVal;

public sealed interface RvarExpression
        extends Expression<RvarVal, Env<RvarVal>>
        permits RvarLetExp, RvarPlusExp, RvarNegExp, RvarNumExp, RvarVarExp, RvarReadExp {

    RvarVal accept(RvarVisitor visitor);
}
