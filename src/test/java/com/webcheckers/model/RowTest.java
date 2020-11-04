package com.webcheckers.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Row} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Model-Tier")
public class RowTest {

    private Row CuT;
    private ArrayList<Space> spaces;

    @BeforeEach
    public void setup() {
        CuT = new Row(8);
    }

    @Test
    public void ctor() {
        final Row CuT = new Row(8);
    }

    @Test
    public void testGetIndex() {
        assertEquals(8, (int) CuT.getIndex());
    }

    @Test
    public void testEquals(){
        Row CuT1 = new Row(5);
        Row CuT2 = new Row(5);

        assertEquals(true,CuT1.equals(CuT2));
        assertEquals(true,CuT2.equals(CuT1));
    }

    @Test
    public void testHashCode() {
        Row CuT1 = new Row(5);
        Row CuT2 = new Row(5);
        assertEquals(CuT1.hashCode(),CuT2.hashCode());
        assertFalse(CuT1.equals(null));
    }
}
