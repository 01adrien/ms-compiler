package mscompiler.rvar.parser;

sealed interface RvarAst permits RvarAstInt, RvarAstRead, RvarAstNeg, RvarAstPlus, RvarAstVar, RvarAstLet {
}

record RvarAstInt(long value) implements RvarAst {
}

record RvarAstRead() implements RvarAst {
}

record RvarAstNeg(RvarAst e) implements RvarAst {
}

record RvarAstPlus(RvarAst e1, RvarAst e2) implements RvarAst {
}

record RvarAstVar(String name) implements RvarAst {
}

record RvarAstLet(String name, RvarAst rhs, RvarAst body) implements RvarAst {
}