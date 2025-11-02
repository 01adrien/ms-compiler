package mscompiler.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private final List<TokenRule> rules;

    private final Pattern pattern;

    public Lexer(List<TokenRule> rules, Pattern pattern) {
        this.rules = rules;
        this.pattern = pattern;
    }

    public List<Token> tokenize(String input) {
        Matcher matcher = pattern.matcher(input);
        List<Token> tokens = new ArrayList<>();

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

            Token token = new Token(rule.type(), str, pos);

            if (!token.type().equals(TokenType.COMMENT)) {
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