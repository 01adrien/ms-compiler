package mscompiler.expression;

import java.util.List;
import java.util.stream.Collectors;

import mscompiler.env.Env;
import mscompiler.env.EnvBinding;
import mscompiler.utils.ListUtils;
import mscompiler.value.LambdaVal;
import mscompiler.value.PrimitiveVal;
import mscompiler.value.Value;

public record ApplicationExp(Expression function, List<Expression> args) implements Expression {

    public ApplicationExp(Expression function, List<Expression> args) {
        this.function = function;
        this.args = List.copyOf(args);
    }

    @Override
    public String toString() {
        String argsStr = args
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        return "(" + function + (argsStr.isEmpty() ? "" : " " + argsStr) + ")";
    }

    @Override
    public Value interpret(Env env) {
        Value fn = function.interpret(env);
        if (fn instanceof LambdaVal lambdaFn) {
            List<EnvBinding> bindings = ListUtils.zip(
                    lambdaFn.params(),
                    args,
                    (param, arg) -> new EnvBinding(param, arg.interpret(env)));
            Env e = lambdaFn.env().extend(bindings);
            return lambdaFn.body().interpret(e);
        } else if (fn instanceof PrimitiveVal primFn) {
            return primFn.value().apply(env, args.toArray(new Expression[0]));
        } else {
            throw new RuntimeException("Not a function: " + fn);
        }
    }

}
