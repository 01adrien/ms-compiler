package mscompiler;

import mscompiler.env.Core;
import mscompiler.env.Env;
import mscompiler.expression.Expression;
import mscompiler.lexer.Lexer;
import mscompiler.lexer.MsLexerBuilder;
import mscompiler.lexer.Token;
import mscompiler.parser.MsParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String input = """
                (define lst (cons 1 (cons 2 3) ))
                (print (filter even? lst))
                (print (reduce * 1 lst))
                                  """;

        Lexer lexer = new MsLexerBuilder().build();

        List<Token> tokens = lexer.tokenize(input);

        MsParser parser = new MsParser();

        List<Expression> expressions = parser.run(tokens);

        Env env = Core.load();

        for (Expression expression : expressions) {
            expression.interpret(env);
        }

    }
}
