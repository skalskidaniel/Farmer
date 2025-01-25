public class Dog extends Movable{

    Dog(int n){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
    }
}
