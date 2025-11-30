package mscompiler.rvar.interpreter;

import mscompiler.rvar.expression.*;
import mscompiler.rvar.value.RvarVal;

public interface RvarVisitor {
    RvarVal visit(RvarNumExp e);

    RvarVal visit(RvarVarExp e);

    RvarVal visit(RvarNegExp e);

    RvarVal visit(RvarPlusExp e);

    RvarVal visit(RvarLetExp e);

    RvarVal visit(RvarReadExp e);
}
