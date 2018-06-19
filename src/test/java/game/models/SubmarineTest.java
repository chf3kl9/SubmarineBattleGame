package game.models;

import game.models.interfaces.ISubmarine;
import game.models.interfaces.ITorpedo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubmarineTest {
    ISubmarine submarine;
    Coördinate startingPoint;
    Torpedo torpedo;


    @Before
    public void prepareTest() {
        startingPoint = new Coördinate(1,1);
        submarine = new Submarine(new Player("0", 0, "Test"), startingPoint, 5, 3, 5, 8, 7);
        torpedo = new Torpedo(new Coördinate(9,1), submarine.getEngineVector(), 0);

    }

    @Test
    public void rotateTest(){
        submarine.rotate(1);
        Assert.assertEquals(5.0f, submarine.getAngle(),  0.0f);
    }

    @Test
    public void accelerateTest(){
        submarine.accelerate(1);
        Assert.assertEquals(5.0f, submarine.getEngineVector().len(),0.0f);
    }

    @Test
    public void moveStepTest(){
        //submarine.rotate(1);
        submarine.accelerate(1);
        submarine.moveStep();

        Assert.assertTrue(submarine.getCoördinate().same(new Coördinate(4,1)));
    }

    @Test
    public void fireTorpedoTest(){
        ITorpedo torpedoFired = submarine.fireTorpedo();
        torpedoFired.moveStep();
        Assert.assertTrue(torpedo.getCoördinate().same(torpedoFired.getCoördinate()));
    }
}
