package mscompiler.lib.parser;

import java.util.List;

import mscompiler.lib.lexer.Token;

@FunctionalInterface
public interface ParseRule<A extends Ast<A>, T extends Token> {
    ParserResult<A, T> parse(List<T> tokens);
}
