package mscompiler.env;

import mscompiler.interpreter.Env;
import mscompiler.interpreter.EnvBinding;
import mscompiler.value.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnvTest {

    @Test
    void testDefineAndLookup() {
        Env env = new Env(null);
        env.define("x", new NumberVal(42.0));
        Value val = env.lookup("x");
        assertTrue(val instanceof NumberVal);
        assertEquals(42.0, ((NumberVal) val).value());
    }

    @Test
    void testExtendWithDoesNotModifyParent() {
        Env parent = new Env(null);
        parent.define("a", new NumberVal(1.0));

        Env child = parent.extend(List.of(
                new EnvBinding("b", new NumberVal(2.0))));

        // parent doit rester inchangé
        assertTrue(parent.isPresent("a"));
        assertFalse(parent.isPresent("b"));

        // child doit contenir a et b
        assertTrue(child.isPresent("a"));
        assertTrue(child.isPresent("b"));
        assertEquals(2.0, ((NumberVal) child.lookup("b")).value());
    }
}
