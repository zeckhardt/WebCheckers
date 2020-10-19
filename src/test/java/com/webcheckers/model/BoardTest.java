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
 * The unit test for the {@link Board}
 *
 * @author Klaus Curde
 */
public class BoardTest {
    // CuT
    private Board CuT;

    // mock objects
    private Integer id;

    @BeforeEach
    public void setup() {
        CuT = new Board();
    }

    @Test
    public void ctor() {
        final Board CuT = new Board();
    }

    @Test
    public void test_makeRows() {
        final Board CuT = new Board();
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void test_getRows() {
        final Board CuT = new Board();
        assertEquals(CuT.getRows().size(), 8);
    }

    @Test
    public void test_equals() {
        final int id = 0;
        final Board CuT0 = new Board();
        final Board CuT1 = new Board();
        assertTrue(CuT0.equals(CuT1));
        assertTrue(CuT1.equals(CuT0));
    }

}
