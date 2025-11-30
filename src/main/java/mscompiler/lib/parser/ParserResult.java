package mscompiler.lib.parser;

import java.util.List;
import mscompiler.lib.lexer.Token;

public record ParserResult<A extends Ast<A>, T extends Token>(
        A node,
        List<T> remainingTokens) {
}