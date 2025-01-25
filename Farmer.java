

public class Farmer extends Movable {
    public int repairState = 0;
    public Dog dog;

    Farmer(int n) {
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        this.dog = new Dog(n);
        field.dogs.add(this.dog);

    }

    private void repair(int X, int Y) {
        repairState -= 1;
        if (repairState == 0) {
            field.grid[X][Y].isDamaged = false;
        }
    }

    private void plant(int X, int Y) {
        field.grid[X][Y].isPlanted = true;
    }

    private void lookForRabbits(int X, int Y) {
        for (int i = 0; i < field.rabbits.size(); ++i) {
            int[] rabbitPosition = field.rabbits.get(i).getPosition();
            int rabbitX = rabbitPosition[0];
            int rabbitY = rabbitPosition[1];
            if (calculateDistance(X, Y, rabbitX, rabbitY) <= 5) {
                notifyDog(i);
            }
        }
    }

    private void notifyDog(int i) {
        if (!dog.isChasing) {
            dog.chasedRabbit = i;
            dog.isChasing = true;
        }
    }

    public void move() {
        if (repairState >0) {
            repair(posX, posY);
        } else {
            moveRandomly();
            int[] position = getPosition();
            int X = position[0];
            int Y = position[1];
            if(field.grid[X][Y].isDamaged){
                repair(X,Y);
            } else if (!field.grid[X][Y].isPlanted){
                plant(X,Y);
            }
            lookForRabbits(X,Y);
        }
    }
}
