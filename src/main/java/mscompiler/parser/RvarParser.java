package mscompiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import mscompiler.lexer.Token;
import mscompiler.lexer.TokenType;
import mscompiler.expression.*;
import mscompiler.expression.rvar.MinusExp;
import mscompiler.expression.rvar.PlusExp;
import mscompiler.expression.rvar.ReadExp;

import static mscompiler.utils.ListUtils.*;

public class RvarParser extends Parser {

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

    private ParserResult parse(List<Token> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case LEFT_PAREN ->
                        parseExpression(skipFirst(toks));
                    case NUMBER ->
                        parseTerminal(toks);
                    case SYMBOL ->
                        parseVar(toks);
                    default ->
                        throw new RuntimeException("Unknow token " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("1 Unexpected end of input"));
    }

    private ParserResult parseExpression(List<Token> tokens) {

        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case PLUS ->
                        parsePlus(skipFirst(toks));
                    case MINUS ->
                        parseMinus(skipFirst(toks));
                    case READ ->
                        parseRead(skipFirst(toks));
                    case LET ->
                        parseLet(skipFirst(toks));
                    default ->
                        throw new RuntimeException("Unknow token " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("1 Unexpected end of input"));
    }

    private ParserResult parseTerminal(List<Token> tokens) {
        Token tok = first(tokens);
        return new ParserResult(new NumberExp(tok.value()), skipFirst(tokens));
    }

    private ParserResult parseRead(List<Token> tokens) {

        return new ParserResult(new ReadExp(), consume(t -> t.isCloseSep(), tokens));

    }

    private ParserResult parsePlus(List<Token> tokens) {
        ParserResult a = parse(tokens);
        ParserResult b = parse(a.remainingTokens());

        return new ParserResult(
                new PlusExp(a.expression(), b.expression()),
                consume(t -> t.isCloseSep(), b.remainingTokens()));

    }

    private ParserResult parseMinus(List<Token> tokens) {
        ParserResult e = parse(tokens);

        return new ParserResult(
                new MinusExp(e.expression()),
                consume(t -> t.isCloseSep(), e.remainingTokens()));

    }

    private ParserResult parseLet(List<Token> tokens) {
        List<Token> toks = consume(t -> t.isOpenParenthesis(), tokens);

        List<LetBinding> bindings = new ArrayList<>();

        Stack<TokenType> separatorStack = new Stack<>();

        while (!firstTokenEqual(t -> t.isCloseParenthesis(), toks)) {
            Token open = first(toks);
            toks = consume(t -> t.isOpenSep(), toks);
            separatorStack.push(open.type());
            String name = first(toks).value();
            ParserResult res = parse(skipFirst(toks));
            bindings.add(new LetBinding(name, res.expression()));

            Token close = first(res.remainingTokens());
            if (!matchSeparators(separatorStack.pop(), close.type())) {
                throw new RuntimeException("Mismatched binding separators in let");
            }

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
                new VarExp(first(tokens).value()),
                skipFirst(tokens));
    }

}
