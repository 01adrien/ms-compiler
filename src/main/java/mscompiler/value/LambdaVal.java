package mscompiler.value;

import java.util.List;

import mscompiler.expression.Expression;
import mscompiler.interpreter.Env;

public record LambdaVal(
        List<String> params, Expression body, Env env)
        implements Value {

    @Override
    public Object value() {
        return this;
    }

    @Override
    public String asString() {
        return "<lambda>";
    }

    @Override
    public ValueType type() {
        return ValueType.LAMBDA;
    }
}
