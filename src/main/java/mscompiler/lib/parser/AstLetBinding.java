package mscompiler.lib.parser;

public record AstLetBinding<A extends Ast<A>>(String id, Ast<A> exp) {

}
