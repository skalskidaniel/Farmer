public class Rabbit extends Movable{

    Rabbit(int n){
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
    }

    public void makeDamage(int X, int Y){
        field.grid[posX][posY].isPlanted = false;
        field.grid[posX][posY].isDamaged = true;
    }

    public void move(){
        moveRandomly();
        makeDamage(posX, posY);
    }
}
