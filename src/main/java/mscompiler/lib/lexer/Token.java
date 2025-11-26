package mscompiler.lib.lexer;

import static mscompiler.lib.lexer.CommonTokenType.*;

public abstract class Token {
    private final TokenType type;
    private final String value;
    private final Position position;

    public Token(TokenType type, String value, Position position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public boolean isOpenParenthesis() {
        return type() == LEFT_PAREN;
    }

    public boolean isCloseParenthesis() {
        return type() == RIGHT_PAREN;
    }

    public boolean isComment() {
        return type() == COMMENT;
    }

    public boolean isOpenSep() {
        return type() == LEFT_BRACKET || type() == LEFT_PAREN;
    }

    public boolean isCloseSep() {
        return type() == RIGHT_BRACKET || type() == RIGHT_PAREN;
    }

    public TokenType type() {
        return type;
    }

    public String value() {
        return value;
    }

    public Position position() {
        return position;
    }

    public int line() {
        return position.line();
    }

    public int column() {
        return position.column();
    }

    @Override
    public String toString() {
        return String.format("%s %s (line %d, col %d)", type, value, line(), column());
    }
}
