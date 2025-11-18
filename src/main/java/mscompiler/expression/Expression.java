package mscompiler.expression;

import mscompiler.interpreter.Env;
import mscompiler.value.Value;

public interface Expression {

    Value interpret(Env env);

    Void generateAsm();
}
