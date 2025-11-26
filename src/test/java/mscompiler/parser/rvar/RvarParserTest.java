package mscompiler.parser.rvar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mscompiler.rvar.env.RvarEnv;
import mscompiler.rvar.expression.RvarExpression;
import mscompiler.rvar.expression.RvarLetBinding;
import mscompiler.rvar.expression.RvarLetExp;
import mscompiler.rvar.expression.RvarNegExp;
import mscompiler.rvar.expression.RvarNumExp;
import mscompiler.rvar.expression.RvarPlusExp;
import mscompiler.rvar.expression.RvarReadExp;
import mscompiler.rvar.lexer.RvarLexerBuilder;
import mscompiler.rvar.lexer.RvarToken;
import mscompiler.rvar.parser.RvarExpMaker;
import mscompiler.rvar.parser.RvarParser;

class RvarParserTest {

    @Test
    void testSimpleNumber() {
        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        RvarParser<Integer, RvarExpression, RvarToken, RvarEnv, RvarLetBinding> parser = new RvarParser<>(
                new RvarExpMaker());

        List<RvarToken> tokens = lexer.tokenize("42");
        List<RvarExpression> expressions = parser.run(tokens);

        assertEquals(1, expressions.size());
        RvarExpression expr = expressions.get(0);
        assertTrue(expr instanceof RvarNumExp);
        assertEquals(42, ((RvarNumExp) expr).interpret(null)); // env pas utilisé pour RvarNumExp
    }

    @Test
    void testNegation() {
        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        RvarParser parser = new RvarParser();

        List<RvarToken> tokens = lexer.tokenize("(- 5)");
        List<RvarExpression> expressions = parser.run(tokens);

        assertEquals(1, expressions.size());
        RvarExpression expr = expressions.get(0);
        assertTrue(expr instanceof RvarNegExp);

        RvarNegExp neg = (RvarNegExp) expr;
        assertEquals(-5, neg.interpret(null));
    }

    @Test
    void testPlusExpression() {
        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        RvarParser parser = new RvarParser();

        List<RvarToken> tokens = lexer.tokenize("(+ 3 4)");
        List<RvarExpression> expressions = parser.run(tokens);

        assertEquals(1, expressions.size());
        RvarExpression expr = expressions.get(0);
        assertTrue(expr instanceof RvarPlusExp);

        RvarPlusExp plus = (RvarPlusExp) expr;
        assertEquals(7, plus.interpret(null));
    }

    @Test
    void testLetExpression() {
        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        RvarParser parser = new RvarParser();

        List<RvarToken> tokens = lexer.tokenize("(let [x 10] (+ x 1))");
        List<RvarExpression> expressions = parser.run(tokens);

        assertEquals(1, expressions.size());
        RvarExpression expr = expressions.get(0);
        assertTrue(expr instanceof RvarLetExp);

        // Tester l'interprétation
        assertEquals(11, expr.interpret(null));
    }

    @Test
    void testReadExpression() {
        RvarLexerBuilder lexerBuilder = new RvarLexerBuilder();
        var lexer = lexerBuilder.build();
        RvarParser parser = new RvarParser();

        List<RvarToken> tokens = lexer.tokenize("(read)");
        List<RvarExpression> expressions = parser.run(tokens);

        assertEquals(1, expressions.size());
        RvarExpression expr = expressions.get(0);
        assertTrue(expr instanceof RvarReadExp);
    }
}
