package mscompiler.rvar.parser;

import mscompiler.lib.env.Env;
import mscompiler.lib.expression.Expression;
import mscompiler.lib.expression.LetBinding;
import mscompiler.lib.lexer.Token;
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

public class RvarParser<O, E extends Expression<O, V>, T extends Token, V extends Env<String, O>, B extends LetBinding<O, V>> {
    public final ParseRule<E, T> parse;
    public final ParseRule<E, T> parseExpression;
    public final ParseRule<E, T> parseNum;
    public final ParseRule<E, T> parseVar;
    public final ParseRule<E, T> parseLet;
    public final ParseRule<E, T> parsePlus;
    public final ParseRule<E, T> parseMinus;
    public final ParseRule<E, T> parseRead;
    public final RvarExpFactory<O, E, V, B> factory;

    public RvarParser(RvarExpFactory<O, E, V, B> _factory) {
        factory = _factory;
        parse = this::parseFn;
        parseExpression = this::parseExpressionFn;
        parseNum = this::parseNumFn;
        parseVar = this::parseVarFn;
        parseLet = this::parseLetFn;
        parsePlus = this::parsePlusFn;
        parseMinus = this::parseMinusFn;
        parseRead = this::parseReadFn;
    }

    public List<E> run(List<T> tokens) {
        List<E> expressions = new ArrayList<>();
        List<T> current = tokens;

        while (!current.isEmpty()) {
            ParserResult<E, T> result = parseFn(current);
            expressions.add(result.expression());
            current = result.remainingTokens();
        }

        return expressions;
    }

    private ParserResult<E, T> parseFn(List<T> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case LEFT_PAREN -> parseExpressionFn(skipFirst(toks));
                    case NUMBER -> parseNumFn(toks);
                    case SYMBOL -> parseVarFn(toks);
                    default -> throw new RuntimeException("Unknown token " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    private ParserResult<E, T> parseExpressionFn(List<T> tokens) {
        return checkNotEmpty(tokens)
                .map(toks -> switch (first(toks).type()) {
                    case PLUS -> parsePlusFn(skipFirst(toks));
                    case MINUS -> parseMinusFn(skipFirst(toks));
                    case READ -> parseReadFn(skipFirst(toks));
                    case LET -> parseLetFn(skipFirst(toks));
                    default -> throw new RuntimeException("Unknown expression " + first(toks).value());
                })
                .orElseThrow(() -> new RuntimeException("Unexpected end of input"));
    }

    private ParserResult<E, T> parseNumFn(List<T> tokens) {
        T tok = first(tokens);
        return new ParserResult<>(
                factory.makeNumber(Integer.parseInt(tok.value())),
                skipFirst(tokens));
    }

    private ParserResult<E, T> parseVarFn(List<T> tokens) {
        return new ParserResult<>(
                factory.makeVar(first(tokens).value()),
                skipFirst(tokens));
    }

    private ParserResult<E, T> parsePlusFn(List<T> tokens) {
        ParserResult<E, T> a = parseFn(tokens);
        ParserResult<E, T> b = parseFn(a.remainingTokens());

        tokens = consume(t -> t.isCloseSep(), b.remainingTokens());

        return new ParserResult<>(
                factory.makePlus(a.expression(), b.expression()), tokens);
    }

    private ParserResult<E, T> parseMinusFn(List<T> tokens) {
        ParserResult<E, T> e = parseFn(tokens);
        tokens = consume(t -> t.isCloseSep(), e.remainingTokens());
        return new ParserResult<>(factory.makeNeg(e.expression()), tokens);
    }

    private ParserResult<E, T> parseReadFn(List<T> tokens) {
        return new ParserResult<>(
                factory.makeRead(),
                consume(t -> t.isCloseSep(), tokens));
    }

    private ParserResult<E, T> parseLetFn(List<T> tokens) {
        // Consommer la parenthèse ouvrante après 'let'
        T open = first(tokens);
        List<T> toks = consume(t -> t.isOpenSep(), tokens);

        List<B> bindings = new ArrayList<>();

        while (!firstTokenEqual(t -> t.isCloseSep(), toks)) {
            // Nom de la variable
            String name = first(toks).value();
            toks = skipFirst(toks);

            // Expression associée
            ParserResult<E, T> res = parseFn(toks);
            bindings.add(factory.makeBinding(name, res.expression()));

            // Vérifier la fermeture de la binding
            T close = first(res.remainingTokens());
            if (!matchSeparators(open.type(), close.type())) {
                throw new RuntimeException("Mismatched binding separators in let");
            }

            // Avancer après la fermeture de la binding
            toks = consume(t -> t.isCloseSep(), res.remainingTokens());
        }

        // Consommer la fermeture de la liste de bindings
        toks = consume(t -> t.isCloseSep(), toks);

        // Parser le corps du let
        ParserResult<E, T> body = parseFn(toks);

        return new ParserResult<>(factory.makeLet(bindings, body.expression()), body.remainingTokens());
    }

}
