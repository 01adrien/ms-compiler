package mscompiler.rvar.parser;

import mscompiler.rvar.expression.RvarVarExp;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarLetBinding;
import mscompiler.rvar.expression.RvarLetExp;
import mscompiler.rvar.expression.RvarNegExp;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.expression.RvarPlusExp;
import mscompiler.rvar.expression.RvarReadExp;

public record RvarExpBuilder(RvarAst ast) {

    public RvarExpression build() {
        return switch (ast) {
            case RvarAstInt i -> new RvarNumExp(i.value());
            case RvarAstVar v -> new RvarVarExp(v.id());
            case RvarAstNeg n -> new RvarNegExp(new RvarExpBuilder(n.a()).build());
            case RvarAstPlus p -> new RvarPlusExp(
                    new RvarExpBuilder(p.a()).build(),
                    new RvarExpBuilder(p.b()).build());
            case RvarAstLet l -> new RvarLetExp(
                    l.bindings().stream().map(RvarLetBinding::new).toList(),
                    new RvarExpBuilder(l.body()).build());
            case RvarAstRead r -> new RvarReadExp();
            default -> null;
        };
    }

}
