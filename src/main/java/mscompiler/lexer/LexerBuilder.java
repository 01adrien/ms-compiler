package mscompiler.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class LexerBuilder {

    private final List<TokenRule> rules = new ArrayList<>();

    public LexerBuilder addRule(TokenType type, String regex, RegexGroup group) {
        rules.add(new TokenRule(type, regex, group));
        return this;
    }

    public Lexer build() {
        String regex = buildRegex().concat("|(\\S+)");
        return new Lexer(rules, Pattern.compile(regex));
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
