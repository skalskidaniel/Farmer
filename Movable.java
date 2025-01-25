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

    protected boolean isValidMove(int X, int Y){
        for(int i = 0; i < field.rabbits.size(); ++i){
            int[] rabbitPosition = field.rabbits.get(i).getPosition();
            int rabbitX = rabbitPosition[0];
            int rabbitY = rabbitPosition[1];
            if (X == rabbitX && Y == rabbitY) {
                return false;
            }
        }
        for(int i = 0; i < field.dogs.size(); ++i){
            int[] dogPosition = field.dogs.get(i).getPosition();
            int dogX = dogPosition[0];
            int dogY = dogPosition[1];
            int[] farmerPosition = field.farmers.get(i).getPosition();
            if ((X == dogX && Y == dogY) || (X == farmerPosition[0] && Y == farmerPosition[1])) {
                return false;
            }
        }
        return true;
    }

    protected void moveRandomly(){
        while(true){
            int direction = randomGenerator.nextInt(4);
            boolean moved = false;
            switch (direction){
                case 0:
                    moveUp();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveDown();
                        break;
                    }
                case 1:
                    moveDown();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveUp();
                        break;
                    }
                case 2:
                    moveLeft();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveRight();
                        break;
                    }
                case 3:
                    moveRight();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveLeft();
                        break;
                    }
            }
            if(moved){
                break;
            }
        }
    }
    
    protected int calculateDistance(int X1, int Y1, int X2, int Y2){
        return Math.abs(X1 - X2) + Math.abs(Y1 - Y2);
    }
}
