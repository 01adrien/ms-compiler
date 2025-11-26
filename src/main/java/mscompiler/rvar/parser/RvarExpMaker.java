package mscompiler.rvar.parser;

import java.util.List;

import mscompiler.lib.expression.Expression;
import mscompiler.rvar.env.RvarEnv;
import mscompiler.rvar.expression.RVarVarExp;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarLetBinding;
import mscompiler.rvar.expression.RvarLetExp;
import mscompiler.rvar.expression.RvarNegExp;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.expression.RvarPlusExp;
import mscompiler.rvar.expression.RvarReadExp;

public class RvarExpMaker implements RvarExpFactory<Integer, RvarExpression, RvarEnv, RvarLetBinding> {

    @Override
    public RvarExpression makeNumber(int n) {
        return new RvarNumExp(n);
    }

    @Override
    public RvarExpression makeVar(String name) {
        return new RVarVarExp(name);
    }

    @Override
    public RvarExpression makeLet(List<RvarLetBinding> envBindings, RvarExpression body) {
        return new RvarLetExp(envBindings, body);
    }

    @Override
    public RvarLetBinding makeBinding(String id, Expression<Integer, RvarEnv> exp) {
        return new RvarLetBinding(id, exp);
    }

    @Override
    public RvarExpression makePlus(RvarExpression a, RvarExpression b) {
        return new RvarPlusExp(a, b);
    }

    @Override
    public RvarExpression makeNeg(RvarExpression e) {
        return new RvarNegExp(e);
    }

    @Override
    public RvarExpression makeRead() {
        return new RvarReadExp();
    }

}
