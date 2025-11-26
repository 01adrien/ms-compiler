package mscompiler.lib.parser;

import static mscompiler.utils.ListUtils.checkNotEmpty;
import static mscompiler.utils.ListUtils.first;
import static mscompiler.utils.ListUtils.skipFirst;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import mscompiler.lib.expression.Expression;
import mscompiler.lib.lexer.Token;

public record One<E extends Expression<?, ?>, T extends Token>(
                Predicate<T> pred,
                Function<T, E> mapper) implements ParseRule<E, T> {

        @Override
        public ParserResult<E, T> parse(List<T> tokens) {
                return checkNotEmpty(tokens)
                                .flatMap(toks -> Optional.of(first(toks))
                                                .filter(pred)
                                                .map(mapper)
                                                .map(expr -> new ParserResult<E, T>(expr, skipFirst(tokens))))
                                .orElseThrow(() -> new RuntimeException("Unexpected token or end of input"));
        }
}
