package mscompiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import mscompiler.expression.Expression;
import mscompiler.lexer.Token;
import mscompiler.lexer.TokenType;

import static mscompiler.utils.ListUtils.*;

public abstract class Parser {

    public abstract List<Expression> run(List<Token> tokens);

    protected List<Token> consume(Predicate<Token> pred, List<Token> tokens) {
        return checkNotEmpty(tokens)
                .filter(toks -> pred.test(first(toks)))
                .map(toks -> skipFirst(toks))
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    protected boolean firstTokenEqual(Predicate<Token> pred, List<Token> tokens) {
        return checkNotEmpty(tokens)
                .map(t -> pred.test(t.get(0)))
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));

    }

    protected boolean matchSeparators(TokenType open, TokenType close) {
        return (open == TokenType.LEFT_PAREN && close == TokenType.RIGHT_PAREN)
                || (open == TokenType.LEFT_BRACKET && close == TokenType.RIGHT_BRACKET);
    }

    protected String errorFromToken(Token token) {
        return String.format("Error at line %d, column %d (%s)", token.line(), token.column(), token);
    }

    protected ParserResult nothing() {
        return new ParserResult(null, new ArrayList<>());
    }

}
