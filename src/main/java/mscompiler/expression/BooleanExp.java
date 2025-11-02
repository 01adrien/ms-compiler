package mscompiler.expression;

import mscompiler.env.Env;
import mscompiler.value.BooleanVal;
import mscompiler.value.Value;

public record BooleanExp(boolean value) implements Expression {

    public BooleanExp(String str) {
        this(parseBoolean(str));
    }

    private static boolean parseBoolean(String str) {
        return switch (str) {
            case "true", "#t" -> true;
            case "false", "#f" -> false;
            default -> throw new RuntimeException("Invalid boolean string: " + str);
        };
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public Value interpret(Env env) {
        return new BooleanVal(value);
    }
}
