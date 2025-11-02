package mscompiler.lexer;

import org.junit.jupiter.api.Test;

import static mscompiler.lexer.TokenType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class LexerTest {

    private Lexer lexer = new MsLexerBuilder().build();

    private void assertToken(List<Token> tokens, int index, TokenType expectedType, String expectedValue) {
        assertEquals(expectedType, tokens.get(index).type());
        assertEquals(expectedValue, tokens.get(index).value());
    }

    @Test
    public void testNumberExpression() {
        String input = "123";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(1, tokens.size());
        assertToken(tokens, 0, NUMBER, "123");
    }

    @Test
    public void testBooleanExpression() {
        String input = "true false";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(2, tokens.size());
        assertToken(tokens, 0, BOOLEAN, "true");
        assertToken(tokens, 1, BOOLEAN, "false");
    }

    @Test
    public void testStringExpression() {
        String input = "\"hello world\"";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(1, tokens.size());
        assertToken(tokens, 0, STRING, "\"hello world\"");
    }

    @Test
    public void testVariableExpression() {
        String input = "myVar";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(1, tokens.size());
        assertToken(tokens, 0, SYMBOL, "myVar");
    }

    @Test
    public void testLambdaExpression() {
        String input = "(lambda (x y) (+ x y))";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(12, tokens.size());

        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, LAMBDA, "lambda");
        assertToken(tokens, 2, LEFT_PAREN, "(");
        assertToken(tokens, 3, SYMBOL, "x");
        assertToken(tokens, 4, SYMBOL, "y");
        assertToken(tokens, 5, RIGHT_PAREN, ")");
        assertToken(tokens, 6, LEFT_PAREN, "(");
        assertToken(tokens, 7, SYMBOL, "+");
        assertToken(tokens, 8, SYMBOL, "x");
        assertToken(tokens, 9, SYMBOL, "y");
        assertToken(tokens, 10, RIGHT_PAREN, ")");
        assertToken(tokens, 11, RIGHT_PAREN, ")");
    }

    @Test
    public void testIfExpression() {
        String input = "(if (> x 0) (+ x 1) (- x 1))";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(18, tokens.size());

        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, IF, "if");
        assertToken(tokens, 2, LEFT_PAREN, "(");
        assertToken(tokens, 3, SYMBOL, ">");
        assertToken(tokens, 4, SYMBOL, "x");
        assertToken(tokens, 5, NUMBER, "0");
        assertToken(tokens, 6, RIGHT_PAREN, ")");
        assertToken(tokens, 7, LEFT_PAREN, "(");
        assertToken(tokens, 8, SYMBOL, "+");
        assertToken(tokens, 9, SYMBOL, "x");
        assertToken(tokens, 10, NUMBER, "1");
        assertToken(tokens, 11, RIGHT_PAREN, ")");
        assertToken(tokens, 12, LEFT_PAREN, "(");
        assertToken(tokens, 13, SYMBOL, "-");
        assertToken(tokens, 14, SYMBOL, "x");
        assertToken(tokens, 15, NUMBER, "1");
        assertToken(tokens, 16, RIGHT_PAREN, ")");
        assertToken(tokens, 17, RIGHT_PAREN, ")");
    }

    @Test
    public void testLetExpression() {
        String input = "(let ([x 10] [y 20]) (+ x y))";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(18, tokens.size());

        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, LET, "let");
        assertToken(tokens, 2, LEFT_PAREN, "(");
        assertToken(tokens, 3, LEFT_BRACKET, "[");
        assertToken(tokens, 4, SYMBOL, "x");
        assertToken(tokens, 5, NUMBER, "10");
        assertToken(tokens, 6, RIGHT_BRACKET, "]");
        assertToken(tokens, 7, LEFT_BRACKET, "[");
        assertToken(tokens, 8, SYMBOL, "y");
        assertToken(tokens, 9, NUMBER, "20");
        assertToken(tokens, 10, RIGHT_BRACKET, "]");
        assertToken(tokens, 11, RIGHT_PAREN, ")");
        assertToken(tokens, 12, LEFT_PAREN, "(");
        assertToken(tokens, 13, SYMBOL, "+");
        assertToken(tokens, 14, SYMBOL, "x");
        assertToken(tokens, 15, SYMBOL, "y");
        assertToken(tokens, 16, RIGHT_PAREN, ")");
        assertToken(tokens, 17, RIGHT_PAREN, ")");
    }

    @Test
    public void testComplexMathExpression() {
        String input = "(* (+ 3 5) (- 10 (/ 20 4)))";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(17, tokens.size());

        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, SYMBOL, "*");
        assertToken(tokens, 2, LEFT_PAREN, "(");
        assertToken(tokens, 3, SYMBOL, "+");
        assertToken(tokens, 4, NUMBER, "3");
        assertToken(tokens, 5, NUMBER, "5");
        assertToken(tokens, 6, RIGHT_PAREN, ")");
        assertToken(tokens, 7, LEFT_PAREN, "(");
        assertToken(tokens, 8, SYMBOL, "-");
        assertToken(tokens, 9, NUMBER, "10");
        assertToken(tokens, 10, LEFT_PAREN, "(");
        assertToken(tokens, 11, SYMBOL, "/");
        assertToken(tokens, 12, NUMBER, "20");
        assertToken(tokens, 13, NUMBER, "4");
        assertToken(tokens, 14, RIGHT_PAREN, ")");
        assertToken(tokens, 15, RIGHT_PAREN, ")");
        assertToken(tokens, 16, RIGHT_PAREN, ")");
    }

    @Test
    public void testLogicalExpressions() {
        String input = "(or (and (> x 5) (< y 10)) (not (eq z 0)))";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(24, tokens.size());

        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, SYMBOL, "or");
        assertToken(tokens, 2, LEFT_PAREN, "(");
        assertToken(tokens, 3, SYMBOL, "and");
        assertToken(tokens, 4, LEFT_PAREN, "(");
        assertToken(tokens, 5, SYMBOL, ">");
        assertToken(tokens, 6, SYMBOL, "x");
        assertToken(tokens, 7, NUMBER, "5");
        assertToken(tokens, 8, RIGHT_PAREN, ")");
        assertToken(tokens, 9, LEFT_PAREN, "(");
        assertToken(tokens, 10, SYMBOL, "<");
        assertToken(tokens, 11, SYMBOL, "y");
        assertToken(tokens, 12, NUMBER, "10");
        assertToken(tokens, 13, RIGHT_PAREN, ")");
        assertToken(tokens, 14, RIGHT_PAREN, ")");
        assertToken(tokens, 15, LEFT_PAREN, "(");
        assertToken(tokens, 16, SYMBOL, "not");
        assertToken(tokens, 17, LEFT_PAREN, "(");
        assertToken(tokens, 18, SYMBOL, "eq");
        assertToken(tokens, 19, SYMBOL, "z");
        assertToken(tokens, 20, NUMBER, "0");
        assertToken(tokens, 21, RIGHT_PAREN, ")");
        assertToken(tokens, 22, RIGHT_PAREN, ")");
    }

    @Test
    public void testApplicationExpression() {
        String input = "(sum 1 2 3)";
        List<Token> tokens = lexer.tokenize(input);

        assertEquals(6, tokens.size());
        assertToken(tokens, 0, LEFT_PAREN, "(");
        assertToken(tokens, 1, SYMBOL, "sum");
        assertToken(tokens, 2, NUMBER, "1");
        assertToken(tokens, 3, NUMBER, "2");
        assertToken(tokens, 4, NUMBER, "3");
        assertToken(tokens, 5, RIGHT_PAREN, ")");
    }

    @Test
    public void testTokenPositions() {
        String input = """
                (define x 10)
                (if (> x 5)
                    (print "ok")
                    (print "no"))
                """;

        List<Token> tokens = lexer.tokenize(input);

        // On vérifie quelques positions précises
        Token define = tokens.get(1);
        assertEquals(1, define.position().line());
        assertEquals(2, define.position().column());

        Token x = tokens.get(2);
        assertEquals(1, x.position().line());
        assertEquals(9, x.position().column());

        Token ifToken = tokens.get(6);
        assertEquals(2, ifToken.position().line());
        assertEquals(2, ifToken.position().column());

        Token stringOk = tokens
                .stream()
                .filter(t -> t.value().equals("\"ok\""))
                .findFirst()
                .orElseThrow();
        assertEquals(3, stringOk.position().line());
        assertEquals(12, stringOk.position().column());
    }

    @Test
    public void testTokenPositionsOnComplexProgram() {
        String input = """
                (define square (lambda (x)
                    (* x x)))

                (define main (lambda ()
                    (if (> (square 3) 9)
                        (print "too big")
                        (print "ok"))))

                (main)
                """;

        List<Token> tokens = lexer.tokenize(input);

        Token define1 = tokens.stream()
                .filter(t -> t.value().equals("define"))
                .findFirst()
                .orElseThrow();
        assertEquals(1, define1.position().line());
        assertEquals(2, define1.position().column());

        Token lambda = tokens.stream()
                .filter(t -> t.value().equals("lambda"))
                .findFirst()
                .orElseThrow();
        assertEquals(1, lambda.position().line()); // même ligne que le define
        assertTrue(lambda.position().column() > define1.position().column());

        Token ifToken = tokens.stream()
                .filter(t -> t.value().equals("if"))
                .findFirst()
                .orElseThrow();
        assertEquals(5, ifToken.position().line());
        assertEquals(6, ifToken.position().column());

        Token stringTooBig = tokens.stream()
                .filter(t -> t.value().equals("\"too big\""))
                .findFirst()
                .orElseThrow();
        assertEquals(6, stringTooBig.position().line());
        assertEquals(16, stringTooBig.position().column());

        Token mainCall = tokens.stream()
                .filter(t -> t.value().equals("main"))
                .skip(1)
                .findFirst()
                .orElseThrow();
        assertEquals(9, mainCall.position().line());
        assertEquals(2, mainCall.position().column());
    }

}