package mscompiler.scheme.lexer;

import mscompiler.lib.lexer.TokenType;

public enum SchemeTokenType implements TokenType {
    NUMBER,
    BOOLEAN,
    STRING,
    LEFT_PAREN,
    RIGHT_PAREN,
    LAMBDA,
    IF,
    LET,
    SYMBOL,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    DEFINE,
    COMMENT,
}
