package mscompiler.lib.env;

import java.util.Optional;

public interface Value {
    Object value();

    default <T> T as(Class<T> type) {
        return Optional
                .ofNullable(value())
                .filter(type::isInstance)
                .map(type::cast)
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot cast %s to %s", value().getClass(), type)));
    }

}
