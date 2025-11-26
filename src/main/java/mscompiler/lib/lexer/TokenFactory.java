package mscompiler.lib.lexer;

@FunctionalInterface
public interface TokenFactory<T extends Token> {
    T create(TokenType type, String value, Position position);
}