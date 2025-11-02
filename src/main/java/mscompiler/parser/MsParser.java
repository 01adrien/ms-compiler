package mscompiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import mscompiler.lexer.Token;
import mscompiler.lexer.TokenType;
import mscompiler.expression.*;
import static mscompiler.utils.ListUtils.*;

public class MsParser {

    private record ParserResult(Expression expression, List<Token> remainingTokens) {
    };

    public List<Expression> run(List<Token> tokens) {
        List<Expression> expressions = new ArrayList<>();
        List<Token> current = tokens;

        while (!current.isEmpty()) {
            ParserResult result = parse(current);
            expressions.add(result.expression());
            current = result.remainingTokens();
        }

        return expressions;
    }

    public ParserResult parse(List<Token> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (toks.get(0).type()) {
                    case LEFT_PAREN ->
                        parseExpression(skipFirst(toks));
                    case SYMBOL ->
                        parseVar(toks);
                    case NUMBER, STRING, BOOLEAN ->
                        parseTerminal(toks);
                    default ->
                        throw new RuntimeException(
                                String.format(errorFromToken(toks.get(0))));
                })
                .orElseThrow(() -> new RuntimeException("1 Unexpected end of input"));
    }

    private ParserResult parseExpression(List<Token> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (toks.get(0).type()) {
                    case IF ->
                        parseIf(skipFirst(toks));
                    case LET ->
                        parseLet(skipFirst(toks));
                    case DEFINE ->
                        parseDefine(skipFirst(toks));
                    case SYMBOL ->
                        parseApplication(toks);
                    case LAMBDA ->
                        parseLambda(skipFirst(toks));
                    default ->
                        throw new RuntimeException(errorFromToken(toks.get(0)));
                })
                .orElseThrow(() -> new RuntimeException("2 Unexpected end of input"));
    }

    private ParserResult parseIf(List<Token> tokens) {
        ParserResult condition = parse(tokens);
        ParserResult consequent = parse(condition.remainingTokens());
        ParserResult alternative = null;

        List<Token> remainingTokens = consequent.remainingTokens();

        if (!firstTokenEqual(t -> t.isCloseSep(), remainingTokens)) {
            alternative = parse(remainingTokens);
            remainingTokens = alternative.remainingTokens();
        }

        List<Token> rest = consume(t -> t.isCloseSep(), remainingTokens);

        return new ParserResult(
                new IfExp(
                        condition.expression(),
                        consequent.expression(),
                        alternative != null ? alternative.expression() : null),
                rest);
    }

    private ParserResult parseTerminal(List<Token> tokens) {

        Token tok = tokens.get(0);

        return switch (tok.type()) {
            case BOOLEAN ->
                new ParserResult(
                        new BooleanExp(tok.value()),
                        skipFirst(tokens));

            case NUMBER ->
                new ParserResult(
                        new NumberExp(tok.value()),
                        skipFirst(tokens));

            case STRING ->
                new ParserResult(
                        new StringExp(tok.value()),
                        skipFirst(tokens));

            default -> throw new RuntimeException(errorFromToken(tok));
        };
    }

    private ParserResult parseLet(List<Token> tokens) {
        List<Token> toks = consume(t -> t.isOpenParenthesis(), tokens);

        List<LetBinding> bindings = new ArrayList<>();

        Stack<TokenType> separatorStack = new Stack<>();

        while (!firstTokenEqual(t -> t.isCloseParenthesis(), toks)) {
            Token open = toks.get(0);
            toks = consume(t -> t.isOpenSep(), toks);
            separatorStack.push(open.type());
            String name = toks.get(0).value();
            ParserResult res = parse(skipFirst(toks));
            bindings.add(new LetBinding(name, res.expression()));

            Token close = res.remainingTokens().get(0);
            if (!matchSeparators(separatorStack.pop(), close.type())) {
                throw new RuntimeException("Mismatched binding separators in let");
            }
            // toks = skipFirst(res.remainingTokens());

            toks = consume(t -> t.isCloseSep(), res.remainingTokens());
        }

        toks = consume(t -> t.isCloseParenthesis(), toks);
        ParserResult res = parse(toks);

        return new ParserResult(
                new LetExp(bindings, res.expression()),
                skipFirst(res.remainingTokens()));
    }

    private ParserResult parseVar(List<Token> tokens) {
        return new ParserResult(
                new VarExp(tokens.get(0).value()),
                skipFirst(tokens));
    }

    private ParserResult parseDefine(List<Token> tokens) {
        String id = tokens.get(0).value();
        ParserResult res = parse(skipFirst(tokens));

        return new ParserResult(
                new DefineExp(id, res.expression()),
                skipFirst(res.remainingTokens()));
    }

    private ParserResult parseLambda(List<Token> tokens) {
        List<Token> toks = consume(t -> t.isOpenParenthesis(), tokens);
        List<String> params = new ArrayList<>();

        while (!firstTokenEqual(t -> t.isCloseParenthesis(), toks)) {
            String param = toks.get(0).value();
            params.add(param);
            toks = skipFirst(toks);
        }

        toks = consume(t -> t.isCloseParenthesis(), toks);

        ParserResult res = parse(toks);

        return new ParserResult(
                new LambdaExp(params, res.expression()),
                skipFirst(res.remainingTokens()));
    }

    private ParserResult parseApplication(List<Token> tokens) {
        ParserResult res = parseVar(tokens);
        Expression function = res.expression();
        List<Expression> args = new ArrayList<>();
        List<Token> toks = res.remainingTokens();

        while (!firstTokenEqual(t -> t.isCloseParenthesis(), toks)) {
            res = parse(toks);
            args.add(res.expression());
            toks = res.remainingTokens();
        }

        return new ParserResult(
                new ApplicationExp(function, args),
                skipFirst(toks));
    }

    private List<Token> consume(Predicate<Token> pred, List<Token> tokens) {
        return checkNotEmpty(tokens)
                .filter(toks -> pred.test(first(toks)))
                .map(toks -> skipFirst(toks))
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    private boolean firstTokenEqual(Predicate<Token> pred, List<Token> tokens) {
        return checkNotEmpty(tokens)
                .map(t -> pred.test(t.get(0)))
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));

    }

    private boolean matchSeparators(TokenType open, TokenType close) {
        return (open == TokenType.LEFT_PAREN && close == TokenType.RIGHT_PAREN)
                || (open == TokenType.LEFT_BRACKET && close == TokenType.RIGHT_BRACKET);
    }

    private String errorFromToken(Token token) {
        return String.format("Error at line %d, column %d", token.line(), token.column());
    }

}
