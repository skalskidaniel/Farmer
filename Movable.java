import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Random;

public abstract class Movable {
    public int posX;
    public int posY;
    protected Field field = Field.getInstance();
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
    
    protected int calculateDistance(int X1, int Y1, int X2, int Y2){
        return Math.abs(X1 - X2) + Math.abs(Y1 - Y2);
    }
}
