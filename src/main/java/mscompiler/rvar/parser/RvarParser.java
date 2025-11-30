package mscompiler.rvar.parser;

import mscompiler.lib.lexer.Token;
import mscompiler.lib.parser.Ast;
import mscompiler.lib.parser.AstFactory;
import mscompiler.lib.parser.AstLetBinding;
import mscompiler.lib.parser.ParseRule;
import mscompiler.lib.parser.ParserResult;

import static mscompiler.lib.lexer.CommonTokenType.LEFT_PAREN;
import static mscompiler.lib.parser.ParserUtils.*;
import static mscompiler.rvar.lexer.RvarTokenType.LET;
import static mscompiler.rvar.lexer.RvarTokenType.MINUS;
import static mscompiler.rvar.lexer.RvarTokenType.NUMBER;
import static mscompiler.rvar.lexer.RvarTokenType.PLUS;
import static mscompiler.rvar.lexer.RvarTokenType.READ;
import static mscompiler.rvar.lexer.RvarTokenType.SYMBOL;
import static mscompiler.utils.ListUtils.checkNotEmpty;
import static mscompiler.utils.ListUtils.first;
import static mscompiler.utils.ListUtils.skipFirst;

import java.util.ArrayList;
import java.util.List;

public class RvarParser<A extends Ast<A>, T extends Token> {
    public final ParseRule<A, T> parse;
    public final ParseRule<A, T> parseExpression;
    public final ParseRule<A, T> parseNum;
    public final ParseRule<A, T> parseVar;
    public final ParseRule<A, T> parseLet;
    public final ParseRule<A, T> parsePlus;
    public final ParseRule<A, T> parseNeg;
    public final ParseRule<A, T> parseRead;

    public final AstFactory<A> factory;

    public RvarParser(AstFactory<A> _factory) {
        factory = _factory;
        parse = this::parseFn;
        parseExpression = this::parseExpressionFn;
        parseNum = this::parseNumFn;
        parseVar = this::parseVarFn;
        parseLet = this::parseLetFn;
        parsePlus = this::parsePlusFn;
        parseNeg = this::parseNegFn;
        parseRead = this::parseReadFn;

    }

    public List<A> run(List<T> tokens) {
        List<A> expressions = new ArrayList<>();
        List<T> current = tokens;

        while (!current.isEmpty()) {
            ParserResult<A, T> result = parseFn(current);
            expressions.add(result.node());
            current = result.remainingTokens();
        }

        return expressions;
    }

    public ParserResult<A, T> parseFn(List<T> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case LEFT_PAREN -> parseExpressionFn(skipFirst(toks));
                    case NUMBER -> parseNumFn(toks);
                    case SYMBOL -> parseVarFn(toks);
                    default -> throw new RuntimeException("Unknown token " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    public ParserResult<A, T> parseExpressionFn(List<T> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case PLUS -> parsePlusFn(skipFirst(toks));
                    case MINUS -> parseNegFn(skipFirst(toks));
                    case READ -> parseReadFn(skipFirst(toks));
                    case LET -> parseLetFn(skipFirst(toks));
                    default -> throw new RuntimeException("Unknown expression " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    public ParserResult<A, T> parseNumFn(List<T> tokens) {
        T tok = first(tokens);
        return new ParserResult<>(
                factory.makeInt(Integer.parseInt(tok.value())),
                skipFirst(tokens));
    }

    public ParserResult<A, T> parseVarFn(List<T> tokens) {
        return new ParserResult<>(
                factory.makeVar(first(tokens).value()),
                skipFirst(tokens));
    }

    public ParserResult<A, T> parsePlusFn(List<T> tokens) {
        ParserResult<A, T> a = parseFn(tokens);
        ParserResult<A, T> b = parseFn(a.remainingTokens());

        tokens = consume(t -> t.isCloseSep(), b.remainingTokens());

        return new ParserResult<>(
                factory.makePlus(a.node(), b.node()), tokens);
    }

    public ParserResult<A, T> parseNegFn(List<T> tokens) {
        ParserResult<A, T> e = parseFn(tokens);
        tokens = consume(t -> t.isCloseSep(), e.remainingTokens());
        return new ParserResult<>(factory.makeNeg(e.node()), tokens);
    }

    public ParserResult<A, T> parseReadFn(List<T> tokens) {
        return new ParserResult<>(
                factory.makeRead(),
                consume(t -> t.isCloseSep(), tokens));
    }

    public ParserResult<A, T> parseLetFn(List<T> tokens) {
        tokens = consume(t -> t.isOpenSep(), tokens);

        List<AstLetBinding<A>> bindings = new ArrayList<>();

        T open;

        while (!firstTokenEqual(t -> t.isCloseSep(), tokens)) {

            open = first(tokens);
            tokens = consume(t -> t.isOpenSep(), tokens);

            String name = first(tokens).value();
            tokens = skipFirst(tokens);

            ParserResult<A, T> res = parseFn(tokens);
            bindings.add(new AstLetBinding<>(name, res.node()));

            T close = first(res.remainingTokens());
            if (!matchSeparators(open.type(), close.type())) {
                throw new RuntimeException("Mismatched binding separators in let");
            }

            tokens = consume(t -> t.isCloseSep(), res.remainingTokens());
        }

        tokens = consume(t -> t.isCloseSep(), tokens);

        ParserResult<A, T> body = parseFn(tokens);

        tokens = consume(t -> t.isCloseSep(), body.remainingTokens());

        return new ParserResult<>(
                factory.makeLet(bindings, body.node()),
                tokens);
    }

}
