package mscompiler.lib.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer<T extends Token> {

    private final List<TokenRule> rules;

    private final Pattern pattern;

    private TokenFactory<T> factory;

    public Lexer(List<TokenRule> rules, Pattern pattern, TokenFactory<T> factory) {
        this.rules = rules;
        this.pattern = pattern;
        this.factory = factory;
    }

    public List<T> tokenize(String input) {
        Matcher matcher = pattern.matcher(input);
        List<T> tokens = new ArrayList<>();

        Position pos = new Position(1, 1);
        int lastEnd = 0;

        while (matcher.find()) {
            String str = matcher.group();
            int start = matcher.start();

            String skipped = input.substring(lastEnd, start);
            pos = advance(skipped, pos);

            TokenRule rule = rules.stream()
                    .filter(r -> r.match(str))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No matching rule for: " + str));

            T token = factory.create(rule.type(), str, pos);

            if (!token.type().equals(CommonTokenType.COMMENT)) {

                tokens.add(token);
            }

            pos = advance(str, pos);
            lastEnd = matcher.end();
        }

        return tokens;

    }

    private Position advance(String text, Position pos) {
        int line = pos.line();
        int col = pos.column();
        for (char c : text.toCharArray()) {
            if (c == '\n') {
                line++;
                col = 1;
            } else {
                col++;
            }
        }
        return new Position(line, col);
    }
}