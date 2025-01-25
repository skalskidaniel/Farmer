
import java.util.ArrayList;


public class Farmer extends Movable{
    /** farmer cannot escape from a field **/
    public int isRepairing = 0;
    public ArrayList<Rabbit> rabbits = new ArrayList<>();
    public Dog dog;

    Farmer(int n, Tile[][] fld, ArrayList<Rabbit> rbs){
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        FIELD_SIZE = n;
        field = fld;
        rabbits = rbs;
        Dog dog = new Dog(n, fld);
    }

    private void repair(int X, int Y){
        // TODO
        isRepairing -= 1;
        if(isRepairing == 0){
            field[X][Y].isDamaged = false;
        }
    }

    private void plant(int X, int Y){
        field[X][Y].isPlanted = true;
    }

    private void lookForRabbits(int X, int Y){

        for (int i = 0; i < rabbits.size(); ++i){
            int[] rabbitPosition = rabbits.get(i).getPosition();
            int rabbitX = rabbitPosition[0];
            int rabbitY = rabbitPosition[1];
            if(calculateDistance(X, Y, rabbitX, rabbitY) <= 5){
                notifyDog(rabbitX, rabbitY);
            }
        }
    }

    private void notifyDog(int rabbitX, int rabbitY){
        dog.findRabbit(rabbitX, rabbitY);
    }

    public Tile[][] move(){
        if(isRepairing > 0){
            repair(posX, posY);
            return field;
        } else{
            int direction = randomGenerator.nextInt(4);
            switch (direction){
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveDown();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
            int[] position = getPosition();
            int X = position[0];
            int Y = position[1];
            if(field[X][Y].isDamaged){
                repair(X,Y);
            } else{
                plant(X,Y);
            }
            lookForRabbits(X,Y);
        }
        return field;
    }
}
