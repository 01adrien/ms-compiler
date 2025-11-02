package mscompiler.parser;

import mscompiler.lexer.Lexer;
import mscompiler.lexer.MsLexerBuilder;
import mscompiler.lexer.Token;
import mscompiler.expression.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

        private final MsParser parser = new MsParser();
        private final Lexer lexer = new MsLexerBuilder().build();

        private Expression parseExpression(String expr) {
                List<Token> tokens = lexer.tokenize(expr);
                return parser.run(tokens).get(0);
        }

        @Test
        void testSimpleIfExpression() {
                assertEquals(
                                new IfExp(
                                                new BooleanExp("true"),
                                                new NumberExp("42"),
                                                null),
                                parseExpression("(if true 42)"));
        }

        @Test
        void testIfExpressionWithElse() {
                assertEquals(
                                new IfExp(
                                                new BooleanExp("false"),
                                                new NumberExp("1"),
                                                new NumberExp("0")),
                                parseExpression("(if false 1 0)"));
        }

        @Test
        void testIfWithBinaryCondition() {
                assertEquals(
                                new IfExp(
                                                new ApplicationExp(new VarExp("<"),
                                                                Arrays.asList(new NumberExp("5"), new NumberExp("10"))),
                                                new NumberExp("1"),
                                                new NumberExp("0")),
                                parseExpression("(if (< 5 10) 1 0)"));
        }

        @Test
        void testIfWithComplexConsequentAndAlternative() {
                assertEquals(
                                new IfExp(
                                                new ApplicationExp(new VarExp("<"),
                                                                Arrays.asList(new NumberExp("5"), new NumberExp("10"))),
                                                new ApplicationExp(new VarExp("+"),
                                                                Arrays.asList(new NumberExp("1"), new NumberExp("2"))),
                                                new ApplicationExp(new VarExp("*"),
                                                                Arrays.asList(new NumberExp("3"), new NumberExp("4")))),
                                parseExpression("(if (< 5 10) (+ 1 2) (* 3 4))"));
        }

        @Test
        void testNestedIfExpression() {
                assertEquals(
                                new IfExp(
                                                new ApplicationExp(new VarExp("and"),
                                                                Arrays.asList(new BooleanExp("true"),
                                                                                new BooleanExp("false"))),
                                                new IfExp(
                                                                new ApplicationExp(new VarExp(">"),
                                                                                Arrays.asList(new NumberExp("10"),
                                                                                                new NumberExp("5"))),
                                                                new NumberExp("1"),
                                                                new NumberExp("2")),
                                                new NumberExp("3")),
                                parseExpression("(if (and true false) (if (> 10 5) 1 2) 3)"));
        }

        @Test
        void testIfWithArithmeticExpressions() {
                assertEquals(
                                new IfExp(
                                                new ApplicationExp(new VarExp("<"),
                                                                Arrays.asList(
                                                                                new NumberExp("8"),
                                                                                new ApplicationExp(new VarExp("+"),
                                                                                                Arrays.asList(new NumberExp(
                                                                                                                "4"),
                                                                                                                new NumberExp("2"))))),
                                                new ApplicationExp(new VarExp("*"),
                                                                Arrays.asList(
                                                                                new NumberExp("3"),
                                                                                new ApplicationExp(new VarExp("-"),
                                                                                                Arrays.asList(new NumberExp(
                                                                                                                "7"),
                                                                                                                new NumberExp("2"))))),
                                                new ApplicationExp(new VarExp("/"),
                                                                Arrays.asList(new NumberExp("9"), new NumberExp("3")))),
                                parseExpression("(if (< 8 (+ 4 2)) (* 3 (- 7 2)) (/ 9 3))"));
        }

        @Test
        void testLetExpression() {
                List<LetBinding> bindings = Arrays.asList(
                                new LetBinding("a", new NumberExp("5")),
                                new LetBinding("b", new NumberExp("8")));
                Expression exp = new ApplicationExp(new VarExp("+"),
                                Arrays.asList(new VarExp("a"), new VarExp("b")));
                assertEquals(
                                new LetExp(bindings, exp),
                                parseExpression("(let ([a 5] [b 8]) (+ a b))"));
        }

        @Test
        void testLambdaExpression() {
                assertEquals(
                                new LambdaExp(Arrays.asList("a", "b"),
                                                new ApplicationExp(new VarExp("+"),
                                                                Arrays.asList(new VarExp("a"), new VarExp("b")))),
                                parseExpression("(lambda (a b) (+ a b))"));
        }

        @Test
        void testApplicationExpression() {
                assertEquals(
                                new ApplicationExp(new VarExp("add3"),
                                                Arrays.asList(new VarExp("a"), new NumberExp("8"), new VarExp("b"))),
                                parseExpression("(add3 a 8 b)"));
        }

        @Test
        void testDefineExpression() {
                assertEquals(
                                new DefineExp("add",
                                                new LambdaExp(Arrays.asList("a", "b"),
                                                                new ApplicationExp(new VarExp("+"),
                                                                                Arrays.asList(new VarExp("a"),
                                                                                                new VarExp("b"))))),
                                parseExpression("(define add (lambda (a b) (+ a b)))"));
        }

}
