package mscompiler.value;

public record NilVal() implements Value {

    public static final NilVal nil = new NilVal();

    @Override
    public Object value() {
        return null;
    }

    @Override
    public String asString() {
        return "()";
    }

    @Override
    public ValueType type() {
        return ValueType.NIL;
    }

}
