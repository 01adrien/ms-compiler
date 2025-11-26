package mscompiler.lib.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class LexerBuilder<T extends Token> {

    private final List<TokenRule> rules = new ArrayList<>();

    private final TokenFactory<T> factory;

    public LexerBuilder(TokenFactory<T> factory) {
        this.factory = factory;
    }

    public LexerBuilder<T> addRule(TokenType type, String regex, RegexGroup group) {
        rules.add(new TokenRule(type, regex, group));
        return this;
    }

    public Lexer<T> build() {
        String regex = buildRegex().concat("|(\\S+)");
        return new Lexer<T>(rules, Pattern.compile(regex), factory);
    }

    public String buildRegex() {
        return rules.stream()
                .collect(Collectors.groupingBy(
                        TokenRule::group,
                        TreeMap::new,
                        Collectors.mapping(TokenRule::regex, Collectors.toList())))
                .values().stream()
                .map(group -> "(" + String.join("|", group) + ")")
                .reduce((a, b) -> a + "|" + b)
                .orElse("");
    }

}
