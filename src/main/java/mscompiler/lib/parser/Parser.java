package mscompiler.lib.parser;

import static mscompiler.lib.lexer.CommonTokenType.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import mscompiler.lib.expression.Expression;
import mscompiler.lib.lexer.Token;
import mscompiler.lib.lexer.TokenType;

public abstract class Parser<E extends Expression<?, ?>, T extends Token> {

    public abstract List<E> run(List<T> tokens);

    protected List<T> consume(Predicate<T> pred, List<T> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Unexpected end of input");
        }
        if (!pred.test(tokens.get(0))) {
            throw new RuntimeException("Unexpected token: " + tokens.get(0));
        }
        return tokens.subList(1, tokens.size());
    }

    protected boolean firstTokenEqual(Predicate<T> pred, List<T> tokens) {
        if (tokens.isEmpty()) {
            throw new RuntimeException("Unexpected end of input");
        }
        return pred.test(tokens.get(0));
    }

    protected boolean matchSeparators(TokenType open, TokenType close) {
        return (open == LEFT_PAREN && close == RIGHT_PAREN)
                || (open == LEFT_BRACKET && close == RIGHT_BRACKET);
    }

    protected String errorFromToken(T token) {
        return String.format("Error at line %d, column %d (%s)",
                token.line(),
                token.column(),
                token);
    }

    protected ParserResult<E, T> nothing() {
        return new ParserResult<>(null, new ArrayList<>());
    }
}
