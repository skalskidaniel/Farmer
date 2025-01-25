import java.util.Random;

public class Tile {
    public String state;
    public int carrotGrowth; // as percentage, if 100 then fully grown, can be eaten
    public boolean isPlanted = false;
    public boolean isDamaged = false;
    private final Random randomGenerator = new Random();


    public void makeDamage(){
        // TODO method would rather be in rabbit class
    }

    public void growCarrot(){
        /* grows carrot by [1;25] % **/
        if (carrotGrowth < 100){
            carrotGrowth += randomGenerator.nextInt(1, 25);
        }
    }
}
