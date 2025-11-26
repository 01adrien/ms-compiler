package mscompiler.scheme.value;

public record ConsVal(Value car, Value cdr) implements Value {
    @Override
    public Object value() {
        return this;
    }

    @Override
    public String asString() {
        return String.format("%s %s", car.asString(), cdr.asString());
    }

    @Override
    public ValueType type() {
        return ValueType.CONS;
    }

}

// (cons 1 (cons 2 3))