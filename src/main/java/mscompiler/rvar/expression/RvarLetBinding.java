package mscompiler.rvar.expression;

import mscompiler.lib.env.Env;
import mscompiler.lib.expression.Expression;
import mscompiler.lib.expression.LetBinding;
import mscompiler.lib.parser.AstLetBinding;
import mscompiler.rvar.parser.RvarAst;
import mscompiler.rvar.parser.RvarExpBuilder;
import mscompiler.rvar.value.RvarVal;

public final class RvarLetBinding extends LetBinding<RvarVal, Env<RvarVal>> {

    public RvarLetBinding(String id, Expression<RvarVal, Env<RvarVal>> exp) {
        super(id, exp);
    }

    public RvarLetBinding(AstLetBinding<RvarAst> b) {
        this(b.id(), new RvarExpBuilder((RvarAst) b.exp()).build());
    }

}
