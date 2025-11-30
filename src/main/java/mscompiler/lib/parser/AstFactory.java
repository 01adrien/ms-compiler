package mscompiler.lib.parser;

import java.util.List;

public interface AstFactory<A extends Ast<A>> {

    A makeInt(Integer n);

    A makeVar(String v);

    A makePlus(A a, A b);

    A makeNeg(A a);

    A makeRead();

    A makeLet(List<AstLetBinding<A>> bindings, A a);
}
