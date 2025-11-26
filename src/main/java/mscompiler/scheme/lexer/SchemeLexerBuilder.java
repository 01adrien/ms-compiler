package mscompiler.scheme.lexer;

import static mscompiler.lib.lexer.RegexGroup.*;
import static mscompiler.scheme.lexer.SchemeTokenType.*;

import mscompiler.lib.lexer.Lexer;
import mscompiler.lib.lexer.LexerBuilder;

public class SchemeLexerBuilder extends LexerBuilder<SchemeToken> {

    public SchemeLexerBuilder() {
        super((type, value, pos) -> new SchemeToken(type, value, pos));
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
    public Lexer<SchemeToken> build() {
        return super.build();
    }
}
