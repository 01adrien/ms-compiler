package mscompiler.rvar.lexer;

import static mscompiler.lib.lexer.CommonTokenType.*;
import static mscompiler.lib.lexer.RegexGroup.*;
import static mscompiler.rvar.lexer.RvarTokenType.*;

import mscompiler.lib.lexer.Lexer;
import mscompiler.lib.lexer.LexerBuilder;

public class RvarLexerBuilder extends LexerBuilder<RvarToken> {

    public RvarLexerBuilder() {
        super((type, value, pos) -> new RvarToken(type, value, pos));

        this
                .addRule(NUMBER, "\\d+", ONE)
                .addRule(READ, "read", TWO)
                .addRule(MINUS, "-", THREE)
                .addRule(PLUS, "\\+", FOUR)
                .addRule(LET, "let", FIVE)
                .addRule(SYMBOL, "[!$%&*/:<=>?@^_~a-zA-Z#][!$%&*/:<=>?@^_~a-zA-Z0-9#]*", SEVEN)
                .addRule(LEFT_PAREN, "\\(", SEVEN)
                .addRule(RIGHT_PAREN, "\\)", SEVEN)
                .addRule(LEFT_BRACKET, "\\[", SEVEN)
                .addRule(RIGHT_BRACKET, "\\]", SEVEN);
    }

    @Override
    public Lexer<RvarToken> build() {
        return super.build();
    }
}
