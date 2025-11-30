package mscompiler.rvar.parser;

import java.util.List;

import mscompiler.lib.parser.Ast;
import mscompiler.lib.parser.AstLetBinding;

public interface RvarAst extends Ast<RvarAst> {
}

record RvarAstInt(int value) implements RvarAst {
}

record RvarAstRead() implements RvarAst {
}

record RvarAstNeg(RvarAst a) implements RvarAst {
}

record RvarAstPlus(RvarAst a, RvarAst b) implements RvarAst {
}

record RvarAstVar(String id) implements RvarAst {
}

record RvarAstLet(List<AstLetBinding<RvarAst>> bindings, RvarAst body) implements RvarAst {
}