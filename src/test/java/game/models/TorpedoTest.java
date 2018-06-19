package game.models;

import game.models.interfaces.IObstacle;
import game.models.interfaces.ITorpedo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TorpedoTest {
    ITorpedo torpedo;
    Coördinate startingPoint;
    Coördinate movedPosition;
    Vector velocityVector;
    IObstacle obstacle;
    ITorpedo collisionTestTorpedo;

    @Before
    public void prepareTest(){
        startingPoint = new Coördinate(0,0);
        movedPosition = new Coördinate(1,1);
        velocityVector = new Vector(1,1);
        torpedo = new Torpedo(startingPoint, velocityVector, 0);
        obstacle = new Obstacle(new Coördinate(2,2), new Coördinate(4,4), false, 0f);
        collisionTestTorpedo = new Torpedo(new Coördinate(3,3), velocityVector, 0);
    }

    @Test
    public void moveStepTest(){
        torpedo.moveStep();
        Assert.assertTrue(torpedo.getCoördinate().same(new Coördinate(1,1)));
    }

    @Test
    public void collisionTest(){
        torpedo.moveStep();
        torpedo.moveStep();
        torpedo.moveStep();
        Assert.assertTrue(torpedo.checkCollision((GameObject) obstacle));
        Assert.assertTrue(torpedo.checkCollision((GameObject)collisionTestTorpedo));
    }
}
