package mscompiler;

import mscompiler.lib.lexer.Lexer;
import mscompiler.lib.parser.One;
import mscompiler.lib.parser.ParserResult;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.lexer.RvarLexerBuilder;
import mscompiler.rvar.lexer.RvarToken;
import mscompiler.rvar.lexer.RvarTokenType;
import mscompiler.rvar.parser.RvarParser;
import mscompiler.scheme.lexer.SchemeLexerBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        // RvarParser parser = new RvarParser();

        List<RvarToken> tokens = lexer.tokenize("(- 5)");
        // List<RvarExpression> expressions = parser.run(tokens);

        // One<RvarExpression, RvarToken> parseOne = new One<>(
        // t -> t.type() == RvarTokenType.NUMBER,
        // t -> new RvarNumExp(t.value()));

        // ParserResult<RvarExpression, RvarToken> res = parseOne.parse(tokens);

    }

    public static void rvar() {
        // String input = """
        // (let ([x (+ 1 2)])
        // (let ([y (- (read))])
        // (+ x (+ y 10))))
        // """;
        // ;

        // Lexer lexer = new RvarLexerBuilder().build();

        // List<Token> tokens = lexer.tokenize(input);

        // RvarParser parser = new RvarParser();

        // List<Expression> expressions = parser.run(tokens);

        // SchemeEnv env = new SchemeEnv(null);

        // for (Expression expression : expressions) {
        // System.out.println(expression.interpret(env));
        // }

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
