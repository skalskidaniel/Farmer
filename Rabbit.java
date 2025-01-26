public class Rabbit extends Movable{
    public int damagingState = 0;
    public boolean isDamaging = false;

    Rabbit(int n){
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
    }

    public void makeDamage(int X, int Y){
        isDamaging = true;
        if (field.grid[X][Y].isPlanted){
            damagingState++;
            if (damagingState == 3){
                field.grid[X][Y].isPlanted = false;
                field.grid[X][Y].isDamaged = true;
                damagingState = 0;
                isDamaging = false;
            }
        }
    }

    public void move(){
        if(field.grid[posX][posY].isPlanted){
            makeDamage(posX, posY);
        } else if(!isDamaging){
            moveRandomly();
        }
    }
}
