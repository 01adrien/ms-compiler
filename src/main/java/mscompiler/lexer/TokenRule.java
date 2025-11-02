package mscompiler.lexer;

public record TokenRule(TokenType type, String regex, RegexGroup group) {

    public boolean match(String str) {
        return str.matches(regex);
    }
}
