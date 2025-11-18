package mscompiler.parser;

import java.util.List;

import mscompiler.expression.Expression;
import mscompiler.lexer.Token;

public record ParserResult(Expression expression, List<Token> remainingTokens) {

}
