package mscompiler.rvar.expression;

import java.util.List;

import mscompiler.rvar.interpreter.RvarVisitor;
import mscompiler.rvar.value.RvarVal;

public record RvarLetExp(List<RvarLetBinding> bindings, RvarExpression exp) implements RvarExpression {

    @Override
    public RvarVal accept(RvarVisitor visitor) {
        return visitor.visit(this);
    }

}
