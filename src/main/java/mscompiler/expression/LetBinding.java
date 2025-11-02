package  mscompiler.expression;

public record LetBinding(String id, Expression exp) {

    @Override
    public final String toString() {
        return String.format("[%s %s]", id, exp.toString());
    }
}