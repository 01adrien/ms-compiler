package mscompiler;

import mscompiler.lib.env.Env;
import mscompiler.lib.lexer.Lexer;
import mscompiler.rvar.compiler.RvarCompiler;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.interpreter.RvarInterpreter;
import mscompiler.rvar.interpreter.RvarVisitor;
import mscompiler.rvar.lexer.RvarLexerBuilder;
import mscompiler.rvar.lexer.RvarToken;
import mscompiler.rvar.parser.RvarAst;
import mscompiler.rvar.parser.RvarAstFactory;
import mscompiler.rvar.parser.RvarExpBuilder;
import mscompiler.rvar.parser.RvarParser;
import mscompiler.rvar.value.RvarVal;

import java.util.HashMap;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    rvar();
  }

  public static void rvar() {

    String i1 = """
        (+ (+ 1 (+ 2 3)) 4)
          """;
    String i2 = "(let ([x 32]) x)";

    Lexer<RvarToken> lexer = new RvarLexerBuilder().build();

    List<RvarToken> tokens = lexer.tokenize(i1);

    RvarParser<RvarAst, RvarToken> parser = new RvarParser<>(new RvarAstFactory());

    List<RvarAst> nodes = parser.run(tokens);

    Env<RvarVal> env = new Env<>(null);

    RvarInterpreter visitor = new RvarInterpreter(env);

    RvarExpression exp = new RvarExpBuilder(nodes.get(0)).build();

    // System.out.println(exp.accept(visitor));

    RvarCompiler compiler = new RvarCompiler();

    System.out.println("SRC");
    System.out.println(i1);
    System.out.println();
    System.out.println("-".repeat(30) + "\n");

    compiler.compile(exp);

    System.out.println();
    System.out.println();

  }

  public static void ms() {
    // String input = "(let ([x 32]) (print (+ (let ([x 10]) x) x)))";

    // Lexer lexer = new SchemeLexerBuilder().build();

    // List<Token> tokens = lexer.tokenize(input);

    // MsParser parser = new MsParser();

    // List<Expression> expressions = parser.run(tokens);

    // SchemeEnv env = Core.load();

    // for (Expression expression : expressions) {
    // System.out.println(expression.interpret(env));
    // }
  }
}
