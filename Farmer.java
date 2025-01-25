

public class Farmer extends Movable {
    public boolean isRepairing = false;
    public int repairProgress = 0;
    public Dog dog;

    Farmer(int n) {
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
        this.dog = new Dog(n);
        field.dogs.add(this.dog);

    }

    private void repair(int X, int Y) {
        // TODO
//        isRepairing -= 1;
//        if (isRepairing == 0) {
//            field.grid[X][Y].isDamaged = false;
//        }
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
                notifyDog(rabbitX, rabbitY);
            }
        }
    }

    private void notifyDog(int rabbitX, int rabbitY) {
        if (!dog.isChasing) {
            dog.findRabbit(rabbitX, rabbitY);
        }
    }

    private boolean isValidMove(int X, int Y) {
        //TODO
        return false;
    }


    public void move() {
        if (isRepairing) {
            repair(posX, posY);
        } else {
            while (true) {
                int direction = randomGenerator.nextInt(4);
                boolean moved = false;
                switch (direction) {
                    case 0:
                        moveUp();
                        if (isValidMove(posX, posY)) {
                            moved = true;
                            break;
                        } else {
                            moveDown();
                            break;
                        }
                    case 1:
                        moveDown();
                        if (isValidMove(posX, posY)) {
                            moved = true;
                            break;
                        } else {
                            moveUp();
                            break;
                        }
                    case 2:
                        moveLeft();
                        if (isValidMove(posX, posY)) {
                            moved = true;
                            break;
                        } else {
                            moveRight();
                            break;
                        }
                    case 3:
                        moveRight();
                        if (isValidMove(posX, posY)) {
                            moved = true;
                            break;
                        } else {
                            moveLeft();
                            break;
                        }
                }
                if (moved) {
                    break;
                }
            }
        }

    }
}
