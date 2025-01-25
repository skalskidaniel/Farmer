public class Rabbit extends Movable{

    Rabbit(int n){
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
    }

    public void makeDamage(int X, int Y){
        field.grid[posX][posY].isDamaged = true;
    }

    public void move(){
        moveRandomly();
        int[] position = getPosition();
        int X = position[0];
        int Y = position[1];
        makeDamage(posX, posY);
    }
}
