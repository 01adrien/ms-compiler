package mscompiler.utils;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {
    public static <T> boolean notEmpty(List<T> list) {
        return !list.isEmpty();
    }

    public static <T> T first(List<T> list) {
        return list.get(0);
    }

    public static <T> List<T> skipFirst(List<T> list) {
        return list.stream().skip(1).toList();
    }

    public static <T> Optional<List<T>> checkNotEmpty(List<T> list) {
        return Optional
                .ofNullable(list)
                .filter(ListUtils::notEmpty);
    }

    public static <A, B, R> List<R> zip(List<A> list1, List<B> list2, BiFunction<A, B, R> zipper) {
        int size = Math.min(list1.size(), list2.size());
        return IntStream.range(0, size)
                .mapToObj(i -> zipper.apply(list1.get(i), list2.get(i)))
                .collect(Collectors.toList());
    }

}
