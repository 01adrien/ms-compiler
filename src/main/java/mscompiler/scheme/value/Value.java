package mscompiler.scheme.value;

import java.util.Optional;

public sealed interface Value
        permits BooleanVal, NumberVal, StringVal, NilVal, ConsVal, SymbolVal {

    Object value();

    ValueType type();

    default <T> T as(Class<T> type) {
        return Optional
                .ofNullable(value())
                .filter(type::isInstance)
                .map(type::cast)
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot cast %s to %s", value().getClass(), type)));
    }

    default boolean eq(Object other) {
        return Optional.ofNullable(other)
                .filter(Value.class::isInstance)
                .map(Value.class::cast)
                .map(o -> {
                    Object v1 = this.value();
                    Object v2 = o.value();
                    return Optional.ofNullable(v1)
                            .map(val -> val.equals(v2) && this.getClass().equals(o.getClass()))
                            .orElse(false);
                })
                .orElse(false);
    }

    default String asString() {
        return value().toString();
    }

}
