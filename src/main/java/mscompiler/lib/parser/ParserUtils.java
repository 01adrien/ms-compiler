package mscompiler.lib.parser;

import static mscompiler.lib.lexer.CommonTokenType.*;

import java.util.List;
import java.util.function.Predicate;

import mscompiler.lib.lexer.Token;
import mscompiler.lib.lexer.TokenType;

public class ParserUtils {
    private ParserUtils() {
    }

    public static <T extends Token> List<T> consume(Predicate<T> pred, List<T> tokens) {
        if (tokens.isEmpty())
            throw new RuntimeException("Unexpected end of input");
        if (!pred.test(tokens.get(0)))
            throw new RuntimeException("Unexpected token: " + tokens.get(0));
        return tokens.subList(1, tokens.size());
    }

    public static <T extends Token> boolean firstTokenEqual(Predicate<T> pred, List<T> tokens) {
        if (tokens.isEmpty())
            throw new RuntimeException("Unexpected end of input");
        return pred.test(tokens.get(0));
    }

    public static boolean matchSeparators(TokenType open, TokenType close) {
        return (open == LEFT_PAREN && close == RIGHT_PAREN)
                || (open == LEFT_BRACKET && close == RIGHT_BRACKET);
    }

    public static <T extends Token, A extends Ast<A>> ParserResult<A, T> nothing() {
        return new ParserResult<>(null, List.of());
    }

    public static <T extends Token> String errorFromToken(T token) {
        return String.format("Error at line %d, column %d (%s)",
                token.line(),
                token.column(),
                token);
    }
}
