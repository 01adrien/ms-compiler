package mscompiler.scheme.value;

public record NumberVal(Double value) implements Value {
    public NumberVal(Integer value) {
        this(value.doubleValue());
    }

    @Override
    public String asString() {
        if (value == Math.floor(value)) {
            return String.valueOf(value.intValue());
        } else {
            return String.valueOf(value);
        }
    }

    @Override
    public ValueType type() {
        return ValueType.NUMBER;
    }

}
