package mscompiler.value;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class ValueTest {

    @Test
    void testBooleanVal() {
        BooleanVal b1 = new BooleanVal(true);
        BooleanVal b2 = new BooleanVal(true);
        BooleanVal b3 = new BooleanVal(false);

        // value() et as()
        assertEquals(true, b1.value());
        assertEquals(true, b1.as(Boolean.class));

        // eq()
        assertTrue(b1.eq(b2));
        assertFalse(b1.eq(b3));

        // asString()
        assertEquals("true", b1.asString());
    }

    @Test
    void testNumberVal() {
        NumberVal i1 = new NumberVal(42);
        NumberVal i2 = new NumberVal(42);
        NumberVal i3 = new NumberVal(7);

        assertEquals(42, i1.value());
        assertEquals(42, i1.as(Double.class));

        assertTrue(i1.eq(i2));
        assertFalse(i1.eq(i3));

        assertEquals("42.0", i1.asString());
    }

    @Test
    void testStringVal() {
        StringVal s1 = new StringVal("hello");
        StringVal s2 = new StringVal("hello");
        StringVal s3 = new StringVal("world");

        assertEquals("hello", s1.value());
        assertEquals("hello", s1.as(String.class));
        assertTrue(s1.eq(s2));
        assertFalse(s1.eq(s3));
        assertEquals("hello", s1.asString());
        assertThrows(RuntimeException.class, () -> s1.as(Integer.class));
        assertFalse(s1.eq(null));
    }

    @Test
    void testAsThrows() {
        BooleanVal b = new BooleanVal(true);

        // Conversion incorrecte doit lancer RuntimeException
        assertThrows(RuntimeException.class, () -> b.as(Integer.class));
    }

    @Test
    void testeqNull() {
        BooleanVal b = new BooleanVal(true);
        assertFalse(b.eq(null));
    }
}
