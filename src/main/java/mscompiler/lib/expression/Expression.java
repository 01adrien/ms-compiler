package mscompiler.lib.expression;

import mscompiler.lib.env.Env;

public interface Expression<O extends Object, V extends Env<?, ?>> {
    O interpret(V env);

    Void generateAsm();
}
