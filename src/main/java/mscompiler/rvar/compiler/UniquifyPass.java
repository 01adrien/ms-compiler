package mscompiler.rvar.compiler;

import java.util.HashMap;
import java.util.List;

import mscompiler.rvar.expression.*;

public class UniquifyPass {

    private HashMap<String, Integer> countVar = new HashMap<>();

    public RvarExpression uniquify(RvarExpression exp, HashMap<String, String> env) {

        return switch (exp) {
            case RvarLetExp e -> uniquifyLet(e, env);
            case RvarPlusExp e -> new RvarPlusExp(uniquify(e.a(), env), uniquify(e.b(), env));
            case RvarNegExp e -> new RvarNegExp(uniquify(e.exp(), env));
            case RvarVarExp e -> new RvarVarExp(env.get(e.id()));
            default -> exp;
        };
    }

    private RvarLetExp uniquifyLet(RvarLetExp exp, HashMap<String, String> env) {
        HashMap<String, String> envLet = new HashMap<>(env);

        List<RvarLetBinding> newBindings = exp
                .bindings()
                .stream()
                .map(b -> {
                    Integer n = countVar.get(b.id());
                    n = n == null ? 1 : n + 1;
                    countVar.put(b.id(), n);
                    String newId = b.id() + "." + n;
                    envLet.put(b.id(), newId);
                    RvarExpression newExp = uniquify((RvarExpression) b.exp(), env);
                    return new RvarLetBinding(newId, newExp);
                })
                .toList();

        RvarExpression newBody = uniquify(exp.exp(), envLet);

        return new RvarLetExp(newBindings, newBody);
    }

    private String freshName(String id, HashMap<String, String> env, int n) {

        String newId = id + "." + n;
        return env.containsValue(newId) ? freshName(newId, env, n + 1) : newId;
    }
}
