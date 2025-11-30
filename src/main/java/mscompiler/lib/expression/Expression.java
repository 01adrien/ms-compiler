package mscompiler.lib.expression;

import mscompiler.lib.env.Env;
import mscompiler.lib.env.Value;

public interface Expression<O extends Value, V extends Env<O>> {
    // <R> R accept(Visitor<R> visitor);
}
