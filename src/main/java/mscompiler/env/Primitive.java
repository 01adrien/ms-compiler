package mscompiler.env;

import mscompiler.expression.Expression;
import mscompiler.value.Value;

@FunctionalInterface
public interface Primitive {
    Value apply(Env env, Expression... args);
}
