package mscompiler.rvar.env;

public record RvarEnvBinding(String id, Integer val) {

    @Override
    public final String toString() {
        return String.format("(%s %s)", id, val.toString());
    }
}