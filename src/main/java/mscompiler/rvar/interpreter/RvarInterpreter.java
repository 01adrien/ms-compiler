package mscompiler.rvar.interpreter;

import java.util.List;
import java.util.Scanner;

import mscompiler.lib.env.Env;
import mscompiler.lib.env.EnvBinding;
import mscompiler.rvar.expression.RvarVarExp;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarLetExp;
import mscompiler.rvar.expression.RvarNegExp;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.expression.RvarPlusExp;
import mscompiler.rvar.expression.RvarReadExp;
import mscompiler.rvar.value.RvarVal;

public class RvarInterpreter implements RvarVisitor {

    private final Env<RvarVal> env;

    public RvarInterpreter(Env<RvarVal> env) {
        this.env = env;
    }

    @Override
    public RvarVal visit(RvarNumExp e) {
        return new RvarVal(e.value());
    }

    @Override
    public RvarVal visit(RvarVarExp e) {
        return new RvarVal(env.lookup(e.id()).value());
    }

    @Override
    public RvarVal visit(RvarNegExp e) {
        return new RvarVal(-1 * e.exp().accept(this).asInt());
    }

    @Override
    public RvarVal visit(RvarPlusExp e) {
        Integer a = e.a().accept(this).asInt();
        Integer b = e.b().accept(this).asInt();
        return new RvarVal(a + b);
    }

    @Override
    public RvarVal visit(RvarLetExp e) {
        List<EnvBinding<RvarVal>> newBindings = e.bindings().stream()
                .map(b -> new EnvBinding<RvarVal>(b.id(), ((RvarExpression) b.exp()).accept(this)))
                .toList();
        Env<RvarVal> newEnv = env.extend(newBindings);
        return e.exp().accept(new RvarInterpreter(newEnv));
    }

    @Override
    public RvarVal visit(RvarReadExp e) {
        return new RvarVal(new Scanner(System.in).nextInt());
    }

}
