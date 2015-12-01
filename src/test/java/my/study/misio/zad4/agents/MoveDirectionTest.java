package my.study.misio.zad4.agents;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.env.TestEnvironment;
import org.junit.Test;

import java.awt.geom.Line2D;
import java.security.InvalidParameterException;

import static org.junit.Assert.*;

/**
 * Created by patryk on 05.10.15.
 */
public class MoveDirectionTest {

    @Test
    public void testMovementX(){
        assertEquals(0.0, MoveDirection.UP.movementX(10), 0.0);
        assertEquals(0.0, MoveDirection.DOWN.movementX(10), 0.0);
        assertEquals(10.0, MoveDirection.RIGHT.movementX(10), 0.0);
        assertEquals(-10.0, MoveDirection.LEFT.movementX(10), 0.0);
    }
    @Test
    public void testMovementY(){
        assertEquals(-10.0, MoveDirection.UP.movementY(10), 0.0);
        assertEquals(10.0, MoveDirection.DOWN.movementY(10), 0.0);
        assertEquals(0.0, MoveDirection.RIGHT.movementY(10), 0.0);
        assertEquals(0.0, MoveDirection.LEFT.movementY(10), 0.0);
    }
    @Test
    public void testIncreaseX(){
        assertEquals(10.0, MoveDirection.UP.increaseX(10), 0.0);
        assertEquals(10.0, MoveDirection.DOWN.increaseX(10), 0.0);
        assertEquals(11.0, MoveDirection.RIGHT.increaseX(10), 0.0);
        assertEquals(9.0, MoveDirection.LEFT.increaseX(10), 0.0);
    }
    @Test
    public void testIncreaseY(){
        assertEquals(9.0, MoveDirection.UP.increaseY(10), 0.0);
        assertEquals(11.0, MoveDirection.DOWN.increaseY(10), 0.0);
        assertEquals(10.0, MoveDirection.RIGHT.increaseY(10), 0.0);
        assertEquals(10.0, MoveDirection.LEFT.increaseY(10), 0.0);
    }

    @Test
    public void testDirectionShouldReturnRight() throws Exception {
        double x1 = 0.0;
        double y1 = 1.0;
        double x2 = 1.0;
        double y2 = 1.0;

        MoveDirection direction = MoveDirection.direction(x1,y1,x2,y2);

        assertEquals(MoveDirection.RIGHT, direction);
    }

    @Test
    public void testDirectionShouldReturnLeft() throws Exception {
        double x1 = 1.0;
        double y1 = 1.0;
        double x2 = 0.0;
        double y2 = 1.0;

        MoveDirection direction = MoveDirection.direction(x1,y1,x2,y2);

        assertEquals(MoveDirection.LEFT, direction);
    }

    @Test
    public void testDirectionShouldReturnDown() throws Exception {
        double x1 = 0.0;
        double y1 = 0.0;
        double x2 = 0.0;
        double y2 = 1.0;

        MoveDirection direction = MoveDirection.direction(x1,y1,x2,y2);

        assertEquals(MoveDirection.DOWN, direction);
    }

    @Test
    public void testDirectionShouldReturnUp() throws Exception {
        double x1 = 0.0;
        double y1 = 1.0;
        double x2 = 0.0;
        double y2 = 0.0;

        MoveDirection direction = MoveDirection.direction(x1,y1,x2,y2);

        assertEquals(MoveDirection.UP, direction);
    }

}