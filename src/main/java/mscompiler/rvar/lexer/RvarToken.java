package mscompiler.rvar.lexer;

import static mscompiler.rvar.lexer.RvarTokenType.*;

import mscompiler.lib.lexer.Position;
import mscompiler.lib.lexer.Token;
import mscompiler.lib.lexer.TokenType;

public class RvarToken extends Token {

    public RvarToken(TokenType type, String value, Position position) {
        super(type, value, position);
    }

    public boolean isNumber() {
        return type() == NUMBER;
    }

    public boolean isSymbol() {
        return type() == SYMBOL;
    }

}
