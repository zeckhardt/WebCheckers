package com.webcheckers.model;

import com.webcheckers.app.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.TemplateEngine;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * The unit test for the {@link BoardView}
 *
 * @author Klaus Curde
 */
public class BoardViewTest {
    // CuT
    private BoardView CuT;

    // mock objects
    private Integer id;

    @BeforeEach
    public void setup() {
//        id = mock(Integer.class);
        id = 0;
        CuT = new BoardView(id);
    }

    @Test
    public void ctor_noArg() {
        assertThrows(NullPointerException.class, () -> {
            final BoardView CuT = new BoardView(null);
        });
    }

    @Test
    public void ctor_withArg() {
        final BoardView CuT = new BoardView(id);
    }

    @Test
    public void test_getId() {
        final BoardView CuT = new BoardView(id);
        assertEquals(java.util.Optional.ofNullable(id), CuT.getId());
    }

    @Test
    public void test_makeRows() {
        final BoardView CuT = new BoardView(id);
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void test_getRows() {
        final BoardView CuT = new BoardView(id);
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void test_equals() {
        final int id = 0;
        final BoardView CuT0 = new BoardView(id);
        final BoardView CuT1 = new BoardView(id);
        assertTrue(CuT0.equals(CuT1));
        assertTrue(CuT1.equals(CuT0));
    }

}
