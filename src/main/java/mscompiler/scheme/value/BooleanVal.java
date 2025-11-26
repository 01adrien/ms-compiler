package mscompiler.scheme.value;

public record BooleanVal(Boolean value) implements Value {

    @Override
    public ValueType type() {
        return ValueType.BOOLEAN;
    }

}
