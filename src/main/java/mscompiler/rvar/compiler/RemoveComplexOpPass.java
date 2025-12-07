package mscompiler.rvar.compiler;

import java.util.ArrayList;
import java.util.List;

import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarLetBinding;
import mscompiler.rvar.expression.RvarLetExp;
import mscompiler.rvar.expression.RvarNegExp;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.expression.RvarPlusExp;
import mscompiler.rvar.expression.RvarVarExp;

public class RemoveComplexOpPass {

    record AtomResult(RvarExpression exp, List<RvarLetBinding> bindings) {
    };

    private static int counter = 1;

    public RvarExpression run(RvarExpression exp) {
        return rcoExp(exp);
    }

    // (+ 52 (- 10))

    public RvarExpression rcoExp(RvarExpression exp) {
        return switch (exp) {
            case RvarPlusExp e -> {
                // d’abord transformer chaque opérande en atome
                AtomResult a1 = rcoAtom(e.a());
                AtomResult a2 = rcoAtom(e.b());

                // construire le cœur (+ a1 a2)
                RvarExpression newPlus = new RvarPlusExp(a1.exp(), a2.exp());

                // maintenant on empile les let si nécessaires
                RvarExpression result = newPlus;
                for (int i = a2.bindings.size() - 1; i >= 0; i--) {
                    result = new RvarLetExp(a2.bindings.get(i), rcoExp(result));
                }
                for (int i = a1.bindings.size() - 1; i >= 0; i--) {
                    result = new RvarLetExp(a1.bindings.get(i), rcoExp(result));
                }

                yield result;
            }

            default -> exp;
        };
    }

    public AtomResult rcoAtom(RvarExpression exp) {
        if (isAtom(exp)) {
            return new AtomResult(exp, List.of());
        }
        // RvarExpression e = rcoExp(exp);
        String name = "tmp." + counter++;
        RvarLetBinding binding = new RvarLetBinding(name, exp);
        return new AtomResult(new RvarVarExp(name), List.of(binding));
    }

    public boolean isAtom(RvarExpression exp) {
        return exp instanceof RvarNumExp || exp instanceof RvarVarExp;
    }

    public boolean isExp(RvarExpression exp) {
        return !isAtom(exp);
    }

}
