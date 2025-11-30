package mscompiler.rvar.expression;

import mscompiler.rvar.interpreter.RvarVisitor;
import mscompiler.rvar.value.RvarVal;

public record RvarNumExp(Integer value) implements RvarExpression {

    @Override
    public RvarVal accept(RvarVisitor visitor) {
        return visitor.visit(this);
    }

}
