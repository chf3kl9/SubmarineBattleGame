package game.models;

import game.models.interfaces.IGameObject;
import game.models.interfaces.ITorpedo;

public class Torpedo extends GameObject implements ITorpedo {

    private float angle;

    public Torpedo(Coördinate coördinate, Vector velocityVector, float angle) {
        this.coördinate = coördinate;
        this.velocityVector = velocityVector;
        this.angle = angle;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    public void moveStep() {
        coördinate.translate(Math.round(velocityVector.getX()), Math.round(velocityVector.getY()));
    }

    public boolean checkCollision(IGameObject other) {
        if (other.getClass() == Obstacle.class){
            Obstacle obstacle = (Obstacle)other;

            if (obstacle.coördinate.getX() <= coördinate.getX() && obstacle.rightBottom.getX() >= coördinate.getX()
                //check whether the torpedo is within the x coördinates of the obstacle
                    &&
                //check whether the torpedo is within they coördinates of the obstacle
                obstacle.coördinate.getY() <= coördinate.getY() && obstacle.rightBottom.getY() >= coördinate.getY())
                return true;
        }
        else if (coördinate.distance(other.getCoördinate()) < 3)//destruction distance needs to be tweaked
            return true;
        return false;
    }
}
