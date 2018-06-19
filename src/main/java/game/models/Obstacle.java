package game.models;

import game.models.interfaces.IObstacle;
import java.util.Random;

public class Obstacle extends GameObject implements IObstacle{

    Coördinate rightBottom;
    boolean movable;
    float randomSpeed;

    public Coördinate getRightBottom() {
        return rightBottom;
    }

    public Obstacle(Coördinate coördinate, Coördinate rightBottom, boolean movable, float randomSpeed){
        this.coördinate = coördinate;
        this.rightBottom = rightBottom;
        this.movable = movable;
        this.randomSpeed = randomSpeed;
        velocityVector = new Vector(0,0);
    }

    public void moveStep() {
        if (movable){
            Vector randomMovement = new Vector(0,0);
            randomMovement.setToRandomDirection();
            Random r = new Random();
            randomMovement.setLength(r.nextFloat() * randomSpeed);
            velocityVector.addVector(randomMovement);
            coördinate.translate(Math.round(velocityVector.getX()), Math.round(velocityVector.getY()));
        }
    }
}
