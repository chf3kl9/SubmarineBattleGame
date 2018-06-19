package game.models;

public class Settings{

    int maxPlayers;
    float maxSpeed;
    float rotationSpeed;
    float reloadTime;
    float acceleration;
    float torpedoSpeed;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getTorpedoSpeed() {
        return torpedoSpeed;
    }

    public Settings(int maxPlayers, float maxSpeed, float rotationSpeed, float reloadTime, float acceleration, float torpedoSpeed) {
        this.maxPlayers = maxPlayers;
        this.maxSpeed = maxSpeed;
        this.rotationSpeed = rotationSpeed;
        this.reloadTime = reloadTime;
        this.acceleration = acceleration;
        this.torpedoSpeed = torpedoSpeed;
    }
}
