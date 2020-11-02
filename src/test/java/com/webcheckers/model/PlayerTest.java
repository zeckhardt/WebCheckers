package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PlayerTest {

    @Test
    public void ctor_noArg() {
        assertThrows(NullPointerException.class, () -> {
            final Player CuT = new Player(null);
        });
    }

    @Test
    public void ctor_withArg() {
        final Player CuT = new Player("test");
    }

    @Test
    public void test_getName() {
        final String name = "test";
        final Player CuT = new Player(name);
        assertEquals(name, CuT.getName());
    }

    @Test
    public void testGetColor(){
        final Player CuT = new Player("test");
        CuT.joinGame(Player.Color.RED);
        assertEquals(Player.Color.RED, CuT.getColor());
    }

    @Test
    public void testIsInGame(){
        final Player CuT = new Player("test");
        CuT.joinGame(Player.Color.WHITE);
        assertTrue(CuT.isInGame());
    }

    @Test
    public void testHashCode() {
        Player CuT1 = new Player("Test");
        Player CuT2 = new Player("Test");
        CuT1.hashCode();
        CuT2.hashCode();
        assertEquals(CuT1.hashCode(),CuT2.hashCode());
    }

    @Test
    public void test_equals() {
        final String name = "test";
        final Player CuT1 = new Player(name);
        final Player CuT2 = new Player(name);
        assertEquals(true, CuT1.equals(CuT2));
        assertEquals(true, CuT2.equals(CuT1));
    }

    @Test
    public void testToString() {
        final Player CuT = new Player("test");
        assertEquals("test",CuT.toString());
    }
}