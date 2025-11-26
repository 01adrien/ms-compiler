package mscompiler.rvar.expression;

import mscompiler.lib.expression.Expression;
import mscompiler.lib.expression.LetBinding;
import mscompiler.rvar.env.RvarEnv;

public final class RvarLetBinding extends LetBinding<Integer, RvarEnv> {

    public RvarLetBinding(String id, Expression<Integer, RvarEnv> exp) {
        super(id, exp);
    }

}
