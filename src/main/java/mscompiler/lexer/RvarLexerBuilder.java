package mscompiler.lexer;

import static mscompiler.lexer.RegexGroup.*;
import static mscompiler.lexer.TokenType.*;

public class RvarLexerBuilder extends LexerBuilder {

    public RvarLexerBuilder() {
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
    public Lexer build() {
        return super.build();
    }
}
