public class Rabbit extends Movable{

    Rabbit(int n){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
    }
}
