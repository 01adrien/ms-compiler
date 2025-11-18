package mscompiler.interpreter;

import mscompiler.value.Value;

public record EnvBinding(String id, Value val) {

    @Override
    public final String toString() {
        return String.format("(%s %s)", id, val.toString());
    }
}