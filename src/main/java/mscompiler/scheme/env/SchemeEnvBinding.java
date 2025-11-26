package mscompiler.scheme.env;

import mscompiler.scheme.value.Value;

public record SchemeEnvBinding(String id, Value val) {

    @Override
    public final String toString() {
        return String.format("(%s %s)", id, val.toString());
    }
}