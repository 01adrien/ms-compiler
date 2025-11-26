package mscompiler.lib.parser;

import java.util.List;

import mscompiler.lib.expression.Expression;
import mscompiler.lib.lexer.Token;

public record ParserResult<E extends Expression<?, ?>, T extends Token>(
                E expression,
                List<T> remainingTokens) {
}