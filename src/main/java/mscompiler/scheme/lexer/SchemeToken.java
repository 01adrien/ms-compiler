package mscompiler.scheme.lexer;

import static mscompiler.scheme.lexer.SchemeTokenType.*;

import mscompiler.lib.lexer.Position;
import mscompiler.lib.lexer.Token;
import mscompiler.lib.lexer.TokenType;

public class SchemeToken extends Token {

    public SchemeToken(TokenType type, String value, Position position) {
        super(type, value, position);
    }

    public boolean isNumber() {
        return type() == NUMBER;
    }

    public boolean isBoolean() {
        return type() == BOOLEAN;
    }

    public boolean isString() {
        return type() == STRING;
    }

    public boolean isTerminal() {
        return isNumber() || isString() || isBoolean();
    }

    public boolean isIf() {
        return type() == IF;
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
                "%s %s (line %d, col %d)", type(), value(), line(), column());
    }
}
