package mscompiler.parser.common;

import org.junit.jupiter.api.Test;

import mscompiler.lib.lexer.Lexer;
import mscompiler.lib.lexer.TokenType;
import mscompiler.lib.parser.One;
import mscompiler.lib.parser.ParserResult;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.lexer.RvarLexerBuilder;
import mscompiler.rvar.lexer.RvarToken;
import mscompiler.rvar.lexer.RvarTokenType;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ParserCombinatorTest {

    @Test
    void testParseOne() {

        String src = "5";

        Lexer<RvarToken> lexer = new RvarLexerBuilder().build();

        List<RvarToken> tokens = lexer.tokenize(src);

        One<RvarExpression, RvarToken> parseOne = new One<>(
                t -> t.type() == RvarTokenType.NUMBER,
                t -> new RvarNumExp(t.value()));

        ParserResult<RvarExpression, RvarToken> res = parseOne.parse(tokens);

        assertTrue(res.expression().equals(new RvarNumExp(5)));

        // RvarParser parser = new RvarParser();

        // List<Expression> expressions = parser.run(tokens);

        // SchemeEnv env = new SchemeEnv(null);

        // for (Expression expression : expressions) {
        // System.out.println(expression.interpret(env));
        // }
    }

}
