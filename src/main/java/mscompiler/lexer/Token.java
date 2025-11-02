package mscompiler.lexer;


import static mscompiler.lexer.TokenType.*;

public record Token(TokenType type, String value, Position position) {

    public boolean isOpenParenthesis() {
        return type == LEFT_PAREN;
    }

    public boolean isCloseParenthesis() {
        return type == RIGHT_PAREN;
    }

    public boolean isNumber() {
        return type == NUMBER;
    }

    public boolean isBoolean() {
        return type == BOOLEAN;
    }

    public boolean isString() {
        return type == STRING;
    }

    public boolean isTerminal() {
        return isNumber() || isString() || isBoolean();
    }

    public boolean isOpenSep() {
        return type == LEFT_BRACKET || type == LEFT_PAREN;
    }

    public boolean isCloseSep() {
        return type == RIGHT_BRACKET || type == RIGHT_PAREN;
    }

    public boolean isIf() {
        return type == IF;
    }

    public int line() {
        return position().line();
    }

    public int column() {
        return position().column();
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s (line %d, col %d)", type, value, line(), column());
    }
}
