package mscompiler.rvar.expression;

import java.util.List;
import java.util.stream.Collectors;

import mscompiler.lib.env.EnvBinding;
import mscompiler.lib.expression.LetBinding;
import mscompiler.rvar.env.RvarEnv;

public record RvarLetExp(List<RvarLetBinding> bindings, RvarExpression exp) implements RvarExpression {

    public RvarLetExp(List<RvarLetBinding> bindings, RvarExpression exp) {
        this.bindings = List.copyOf(bindings);
        this.exp = exp;
    }

    @Override
    public String toString() {
        String bindingsStr = bindings.stream()
                .map(LetBinding::toString)
                .collect(Collectors.joining(" "));
        return "(let (" + bindingsStr + ") " + exp + ")";
    }

    @Override
    public Integer interpret(RvarEnv env) {
        List<EnvBinding<String, Integer>> newBindings = bindings.stream()
                .map(b -> new EnvBinding<>(b.id(), b.exp().interpret(env)))
                .toList();

        // Étendre l'environnement et évaluer le corps
        return exp.interpret(env.extend(newBindings));
    }

    @Override
    public Void generateAsm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateAsm'");
    }

}
