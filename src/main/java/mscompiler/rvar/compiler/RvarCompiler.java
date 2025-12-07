package mscompiler.rvar.compiler;

import mscompiler.rvar.expression.*;

public class RvarCompiler {

    // private HashMap<String, String> env = ;

    public void compile(RvarExpression exp) {
        RvarExpression e1 = uniquify(exp);
        RvarExpression e2 = removeComplexOp(e1);

    }

    private RvarExpression uniquify(RvarExpression exp) {
        System.out.println("UNIQUIFY");
        RvarExpression res = new UniquifyPass().run(exp);
        print(res, 0);
        System.out.println("\n\n" + "-".repeat(30) + "\n");
        return res;

    }

    private RvarExpression removeComplexOp(RvarExpression exp) {
        System.out.println("REMOVE COMPLEX OP");
        RvarExpression res = new RemoveComplexOpPass().run(exp);
        print(res, 0);
        System.out.println("\n\n" + "-".repeat(30) + "\n");
        return res;
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
