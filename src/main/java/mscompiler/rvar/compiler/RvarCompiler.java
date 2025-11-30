package mscompiler.rvar.compiler;

import java.util.HashMap;

import mscompiler.rvar.expression.*;

public class RvarCompiler {

    private HashMap<String, String> env = new HashMap<>();

    public void compile(RvarExpression exp) {
        RvarExpression uExp = uniquify(exp);
        System.out.println();
        print(uExp, 0);

    }

    private RvarExpression uniquify(RvarExpression exp) {
        return new UniquifyPass().uniquify(exp, env);
    }

    public void print(RvarExpression exp, int space) {
        String indent = " ".repeat(space);

        switch (exp) {
            case RvarLetExp e -> {
                System.out.print(indent + "(let (");
                for (RvarLetBinding b : e.bindings()) {
                    System.out.print("[" + b.id() + " ");
                    print((RvarExpression) b.exp(), 0);
                    System.out.print("]");
                }
                System.out.println(")");
                print(e.exp(), space + 2);
                System.out.print(")");
            }
            case RvarPlusExp e -> {
                System.out.print(indent + "(+ ");
                print(e.a(), 0);
                System.out.print(" ");
                print(e.b(), 0);
                System.out.print(")");
            }
            case RvarNegExp e -> {
                System.out.print(indent + "(- ");
                print(e.exp(), 0);
                System.out.print(")");
            }
            case RvarVarExp e -> System.out.print(indent + e.id());
            case RvarReadExp e -> System.out.print(indent + "(read)");
            case RvarNumExp e -> System.out.print(indent + e.value());
        }
    }

}
