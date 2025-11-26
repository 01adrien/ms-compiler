package mscompiler.lexer.rvar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import mscompiler.rvar.lexer.RvarLexerBuilder;
import mscompiler.rvar.lexer.RvarToken;

import static mscompiler.lib.lexer.CommonTokenType.*;
import static mscompiler.rvar.lexer.RvarTokenType.*;

class RvarLexerTest {

    @Test
    void testSimpleExpression() {
        // Créer le lexer
        RvarLexerBuilder builder = new RvarLexerBuilder();
        var lexer = builder.build();

        // Expression à lexer
        String input = "(let [x 10] (+ x 1))";

        List<RvarToken> tokens = lexer.tokenize(input);

        assertEquals(12, tokens.size(), "Nombre de tokens");

        // Vérifier le premier token
        assertEquals(LEFT_PAREN, tokens.get(0).type());
        assertEquals("(", tokens.get(0).value());

        // Vérifier le 'let'
        assertEquals(LET, tokens.get(1).type());
        assertEquals("let", tokens.get(1).value());

        // Vérifier le 'x'
        assertEquals(SYMBOL, tokens.get(3).type());
        assertEquals("x", tokens.get(3).value());

        // Vérifier le nombre 10
        assertEquals(NUMBER, tokens.get(4).type());
        assertEquals("10", tokens.get(4).value());

        // Vérifier les opérateurs
        assertEquals(PLUS, tokens.get(7).type());
        assertEquals("+", tokens.get(7).value());

        // Vérifier la fermeture de la parenthèse finale
        assertEquals(RIGHT_PAREN, tokens.get(tokens.size() - 1).type());
        assertEquals(")", tokens.get(tokens.size() - 1).value());
    }

    @Test
    void testEmptyInput() {
        RvarLexerBuilder builder = new RvarLexerBuilder();
        var lexer = builder.build();

        List<RvarToken> tokens = lexer.tokenize("");
        assertTrue(tokens.isEmpty(), "Liste de tokens doit être vide");
    }

    @Test
    void testReadToken() {
        RvarLexerBuilder builder = new RvarLexerBuilder();
        var lexer = builder.build();

        String input = "(read)";
        List<RvarToken> tokens = lexer.tokenize(input);

        assertEquals(READ, tokens.get(1).type());
        assertEquals("read", tokens.get(1).value());
    }
}
