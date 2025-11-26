package mscompiler.lib.parser;

import java.util.List;

import mscompiler.lib.expression.Expression;
import mscompiler.lib.lexer.Token;

@FunctionalInterface
public interface ParseRule<E extends Expression<?, ?>, T extends Token> {
    ParserResult<E, T> parse(List<T> tokens);
}
