package mscompiler.scheme.value;

public record StringVal(String value) implements Value {

    @Override
    public String asString() {
        return value.substring(1, value.length() - 1);
    }

    @Override
    public ValueType type() {
        return ValueType.STRING;
    }
}
