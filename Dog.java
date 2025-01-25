public class Dog extends Movable{

    Dog(int n, Tile[][] fld){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
        field = fld;
    }

    public void findRabbit(int rabbitX, int rabbitY){
        //TODO
    }
}
