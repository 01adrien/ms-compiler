package mscompiler.scheme.value;

public record SymbolVal(String value) implements Value {

    @Override
    public ValueType type() {
        return ValueType.SYMBOL;
    }

}
