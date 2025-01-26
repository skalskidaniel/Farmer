import java.util.Random;
import static java.lang.Math.min;

public class Tile {
    public int carrotGrowth; // as percentage, if 100 then fully grown, can be eaten
    public boolean isPlanted = false;
    public boolean isDamaged = false;
    private final Random randomGenerator = new Random();

    public void growCarrot(){
        if (carrotGrowth < 100){
            carrotGrowth = min(100, carrotGrowth + randomGenerator.nextInt(20, 50));
        }
    }
}
