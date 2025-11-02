package mscompiler.value;

import mscompiler.env.Primitive;

public record PrimitiveVal(Primitive value) implements Value {

    @Override
    public String asString() {
        return "<primitive>";
    }

    @Override
    public ValueType type() {
        return ValueType.PRIMITIVE;
    }
}
