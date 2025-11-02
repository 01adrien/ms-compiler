package mscompiler.expression;

import java.util.List;
import java.util.stream.Collectors;

import mscompiler.env.Env;
import mscompiler.env.EnvBinding;
import mscompiler.value.Value;


public record LetExp(List<LetBinding> bindings, Expression exp) implements Expression {

    public LetExp(List<LetBinding> bindings, Expression exp) {
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
    public Value interpret(Env env) {
        List<EnvBinding> newBindings = bindings
                .stream()
                .map((b) -> new EnvBinding(b.id(), b.exp().interpret(env)))
                .toList();
        return exp.interpret(env.extend(newBindings));
    }

}
