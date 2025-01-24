import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Movable {
    int posX;
    int posY;
    protected int FIELD_SIZE;
    protected final Random randomGenerator = new Random();

    public int[] getPosition(){
        return new int[] {posX, posY};
    }

    protected void moveUp(){
        posX = max(posX - 1, 0);
    }

    protected void moveDown(){
        posX = min(posX + 1, FIELD_SIZE - 1);
    }

    protected void moveLeft(){
        posY = max(posY - 1, 0);
    }

    protected void moveRight(){
        posY = min(posY + 1, FIELD_SIZE - 1);
    }
}
