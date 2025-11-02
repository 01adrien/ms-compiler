package mscompiler.utils;

import java.util.Optional;

public class ExceptionUtils {

    @FunctionalInterface
    public interface ExceptionSupplier<T> {
        T get() throws Exception;
    }

    public static <T> Optional<T> tryOf(ExceptionSupplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
