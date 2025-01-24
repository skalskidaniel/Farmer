import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Random;

public class Farmer extends Movable{
    /** farmer cannot escape from a field **/

    Farmer(int n){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
    }
}
