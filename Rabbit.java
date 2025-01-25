public class Rabbit extends Movable{

    Rabbit(int n, Tile[][] fld){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
        field = fld;
    }
}
