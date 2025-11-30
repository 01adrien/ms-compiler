package mscompiler.rvar.parser;

import java.util.List;

import mscompiler.lib.parser.AstFactory;
import mscompiler.lib.parser.AstLetBinding;

public class RvarAstFactory implements AstFactory<RvarAst> {

    @Override
    public RvarAst makeInt(Integer n) {
        return new RvarAstInt(n);
    }

    @Override
    public RvarAst makeVar(String name) {
        return new RvarAstVar(name);
    }

    @Override
    public RvarAst makeLet(List<AstLetBinding<RvarAst>> bindings, RvarAst body) {
        return new RvarAstLet(bindings, body);
    }

    @Override
    public RvarAst makePlus(RvarAst a, RvarAst b) {
        return new RvarAstPlus(a, b);
    }

    @Override
    public RvarAst makeNeg(RvarAst a) {
        return new RvarAstNeg(a);
    }

    @Override
    public RvarAst makeRead() {
        return new RvarAstRead();
    }

}
