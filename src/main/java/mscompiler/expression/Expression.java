package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.Value;

public interface Expression {

    public Value interpret(Env env);
}
