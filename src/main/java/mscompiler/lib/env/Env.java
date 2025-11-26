package mscompiler.lib.env;

import java.util.HashMap;
import java.util.List;

public class Env<K, V> implements Cloneable {

    private final HashMap<K, V> env = new HashMap<>();

    public Env(List<EnvBinding<K, V>> bindings) {
        if (bindings != null) {
            bindings.forEach(b -> env.put(b.id(), b.val()));
        }
    }

    public V lookup(K key) {
        if (!isPresent(key)) {
            throw new RuntimeException("Unbound symbol: " + key);
        }
        return env.get(key);
    }

    public boolean isPresent(K key) {
        return env.containsKey(key);
    }

    public Env<K, V> extend(List<EnvBinding<K, V>> bindings) {
        Env<K, V> extended = this.clone();
        bindings.forEach(b -> extended.env.put(b.id(), b.val()));
        return extended;
    }

    @Override
    public Env<K, V> clone() {
        Env<K, V> copy = new Env<>(null);
        copy.env.putAll(this.env);
        return copy;
    }

    @Override
    public String toString() {
        return env.keySet().toString();
    }

    public void print() {
        env.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @SuppressWarnings("unchecked")
    public <T extends Env<K, V>> T cast(Class<T> cls) {
        if (!cls.isInstance(this)) {
            throw new ClassCastException(
                    "Cannot cast " + this.getClass().getName() + " to " + cls.getName());
        }
        return (T) this;
    }
}
