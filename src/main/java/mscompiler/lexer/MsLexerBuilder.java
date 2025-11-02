package mscompiler.lexer;

import static mscompiler.lexer.RegexGroup.*;
import static mscompiler.lexer.TokenType.*;

public class MsLexerBuilder extends LexerBuilder {

    public MsLexerBuilder() {
        this
                .addRule(NUMBER, "\\d+(\\.\\d+|,\\d+)|\\d+", ONE)
                .addRule(BOOLEAN, "true|false|#t|#f", TWO)
                .addRule(STRING, "\"(.*?)\"", THREE)
                .addRule(LAMBDA, "lambda", FOUR)
                .addRule(IF, "if", FOUR)
                .addRule(LET, "let", FOUR)
                .addRule(DEFINE, "define", FOUR)
                .addRule(SYMBOL, "[!$%&*/:<=>?@^_~a-zA-Z#+-][!$%&*/:<=>?@^_~a-zA-Z0-9#+-]*", FIVE)
                .addRule(LEFT_PAREN, "\\(", SIX)
                .addRule(RIGHT_PAREN, "\\)", SIX)
                .addRule(LEFT_BRACKET, "\\[", SIX)
                .addRule(RIGHT_BRACKET, "\\]", SIX)
                .addRule(COMMENT, ";.*", SEVEN);

    }

    @Override
    public Lexer build() {
        return super.build();
    }
}
