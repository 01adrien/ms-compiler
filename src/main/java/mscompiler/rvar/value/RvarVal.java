package mscompiler.rvar.value;

import mscompiler.lib.env.Value;

public record RvarVal(Integer value) implements Value {
    public Integer asInt() {
        return as(Integer.class);
    }
}
