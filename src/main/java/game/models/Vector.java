package game.models;

import com.badlogic.gdx.math.MathUtils;

public class Vector {

    private float x;
    private float y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setZero(){
        x=0;
        y=0;
    }

    public void setAngle(float angle){
        set(len(), 0);
        rotate(angle);
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float len(){
        return (float)Math.sqrt(x*x+y*y);
    }


    public void setLength(float length){
        if (x*x+y*y!=0) {

            float oldLen2 = x * x + y * y;
            scl((float) Math.sqrt((double) (length * length / oldLen2)));

        }
    }

    public void scl(float scalar){
        x *= scalar;
        y *= scalar;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void addVector(Vector other){
        this.x += other.getX();
        this.y += other.getY();
    }

    public void rotate(float angle){
        float radians = angle * 0.017453292F;
        float cos = (float)Math.cos((double)radians);
        float sin = (float)Math.sin((double)radians);
        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
    }
    public void setToRandomDirection(){
        float theta = MathUtils.random(0.0F, 6.2831855F);
        set(MathUtils.cos(theta), MathUtils.sin(theta));
    }
}
