package mscompiler.rvar.expression;

import mscompiler.rvar.interpreter.RvarVisitor;
import mscompiler.rvar.value.RvarVal;

public record RvarPlusExp(RvarExpression a, RvarExpression b) implements RvarExpression {

    @Override
    public RvarVal accept(RvarVisitor visitor) {
        return visitor.visit(this);
    }

}
