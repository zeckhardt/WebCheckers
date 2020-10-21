package com.webcheckers.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * The unit test for the {@link Move} component.
 *
 * @author Zach Eckhardt
 */
@Tag("Model-Tier")
public class MoveTest {

    private Move CuT;

    @BeforeEach
    public void setup(){CuT = new Move(4,5,5,6);}

    @Test
    public void ctor(){final Move CuT = new Move(1,1,2,2);}

    @Test
    public void testGetStartRow(){ assertEquals(4, CuT.getStartRow());}

    @Test
    public void testGetStartCell(){ assertEquals(5,CuT.getStartCell());}

    @Test
    public void testGetEndRow(){ assertEquals(5, CuT.getEndRow());}

    @Test
    public void testGetEndCell(){ assertEquals(6, CuT.getEndCell());}

}
