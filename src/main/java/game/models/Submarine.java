package game.models;

import game.models.interfaces.IGameObject;
import game.models.interfaces.ISubmarine;
import game.models.interfaces.ITorpedo;

public class Submarine extends GameObject implements ISubmarine {

    Player player;
    Vector engineVector;
    float rotationSpeed;
    float reloadTime;
    float reloadedTime;
    float acceleration;
    float torpedoSpeed;
    float drag;
    float angle = 0;

    final static double visionRange = 125;

    public Player getPlayer() {
        return player;
    }

    public Vector getEngineVector() {
        return engineVector;
    }

    public float getAngle(){
        return angle;
    }

    public Submarine (Player player, Coördinate coördinate, float rotationSpeed, float reloadTime, float acceleration, float torpedoSpeed, float maxSpeed) {
        this.player = player;
        this.coördinate = coördinate;
        this.engineVector = new Vector(0,0);
        this.velocityVector = new Vector(0,0);
        this.rotationSpeed = rotationSpeed;
        this.reloadTime = reloadTime * 1000;
        this.reloadedTime = 0;
        this.acceleration = acceleration;
        this.torpedoSpeed = torpedoSpeed;
        this.drag = acceleration / (acceleration+maxSpeed);
    }

    public void moveStep() {
        velocityVector.addVector(engineVector);
        velocityVector.scl(1-drag);
        coördinate.translate(Math.round(velocityVector.getX()), Math.round(velocityVector.getY()));
    }


    public ITorpedo fireTorpedo() {
        if (System.currentTimeMillis() >= reloadedTime) {
            Vector torpedoVector = new Vector(1, 1);
            torpedoVector.setLength(torpedoSpeed);
            torpedoVector.setAngle(angle);
            reloadedTime = System.currentTimeMillis() + reloadTime;
            return new Torpedo(coördinate, torpedoVector, angle);
        }
        return null;
    }

    public void rotate(int direction) {

        if (direction == -1) {
            engineVector.rotate(-rotationSpeed);
            angle = (angle - rotationSpeed + 360F)%360;
        } else if (direction == 1) {
            engineVector.rotate(rotationSpeed);
            angle = (angle + rotationSpeed)%360;
        }
    }

    public void accelerate(int direction) {
        engineVector.set(1,1);
        if (direction == -1) {
            engineVector.setLength(acceleration);
            engineVector.setAngle(angle+180);
        }
        else if (direction == 0)
            engineVector.setZero();
        else if (direction == 1) {
            engineVector.setLength(acceleration);
            engineVector.setAngle(angle);
        }
    }

    public boolean canSee(IGameObject other){
        return other.getCoördinate().distance(getCoördinate()) < visionRange;
    }
}
