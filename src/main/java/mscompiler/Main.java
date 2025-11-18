package mscompiler;

import mscompiler.expression.Expression;
import mscompiler.interpreter.Core;
import mscompiler.interpreter.Env;
import mscompiler.lexer.Lexer;
import mscompiler.lexer.MsLexerBuilder;
import mscompiler.lexer.RvarLexerBuilder;
import mscompiler.lexer.Token;
import mscompiler.parser.MsParser;
import mscompiler.parser.RvarParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // ms();
        rvar();
    }

    public static void rvar() {
        String input = """
                (let ([x (+ 1 2)])
                    (let ([y (- (read))])
                      (+ x (+ y 10))))
                                  """;
        ;

        Lexer lexer = new RvarLexerBuilder().build();

        List<Token> tokens = lexer.tokenize(input);

        RvarParser parser = new RvarParser();

        List<Expression> expressions = parser.run(tokens);

        Env env = new Env(null);

        for (Expression expression : expressions) {
            System.out.println(expression.interpret(env));
        }

    }

    public static void ms() {
        String input = "(let ([x 32])  (print (+ (let ([x 10]) x) x)))";

        Lexer lexer = new MsLexerBuilder().build();

        List<Token> tokens = lexer.tokenize(input);

        MsParser parser = new MsParser();

        List<Expression> expressions = parser.run(tokens);

        Env env = Core.load();

        for (Expression expression : expressions) {
            System.out.println(expression.interpret(env));
        }
    }
}
