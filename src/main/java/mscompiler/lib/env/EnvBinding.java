package mscompiler.lib.env;

public record EnvBinding<V extends Value>(String id, V val) {
}