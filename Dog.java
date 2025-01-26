public class Dog extends Movable{
    public boolean isChasing = false;
    int chasedRabbit = -1;

    Dog(int n){
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
    }

    private void lookForRabbits(int X, int Y) {
        for (int i = 0; i < field.rabbits.size(); ++i) {
            int[] rabbitPosition = field.rabbits.get(i).getPosition();
            int rabbitX = rabbitPosition[0];
            int rabbitY = rabbitPosition[1];
            if (calculateDistance(X, Y, rabbitX, rabbitY) <= 5) {
                isChasing = true;
                chasedRabbit = i;
            }
        }
    }

    public boolean isValidMove(int X, int Y) {
        int counter = 0;
        for (int i = 0; i < field.dogs.size(); ++i) {
            int[] dogPosition = field.dogs.get(i).getPosition();
            int dogX = dogPosition[0];
            int dogY = dogPosition[1];
            int[] farmerPosition = field.farmers.get(i).getPosition();
            int farmerX = farmerPosition[0];
            int farmerY = farmerPosition[1];
            if ((X == dogX && Y == dogY) || (X == farmerX && Y == farmerY)) {
                counter++;
            }
        }
        if (counter != 1) {
            return false;
        }
        return true;
    }

    public void findRabbit(int i){
        chasedRabbit = i;
        isChasing = true;
        int[] rabbitPosition = field.rabbits.get(i).getPosition();
        int rabbitX = rabbitPosition[0];
        int rabbitY = rabbitPosition[1];
        if(calculateDistance(posX, posY, rabbitX, rabbitY) > 5){
            isChasing = false;
            chasedRabbit = -1;
            moveRandomly();
            return;
        }
        boolean moved = false;
        int[] checked = {0,0,0,0};
        int oldPosX = posX;
        int oldPosY = posY;
        while(!moved) {
            int direction = randomGenerator.nextInt(4);
            switch (direction) {
                case 0:
                    moveUp();
                    if (isValidMove(posX, posY) && (calculateDistance(posX, posY, rabbitX, rabbitY) < calculateDistance(oldPosX, oldPosY, rabbitX, rabbitY))) {
                        moved = true;
                        break;
                    } else {
                        moveDown();
                        checked[0] = 1;
                        break;
                    }
                case 1:
                    moveDown();
                    if (isValidMove(posX, posY) && (calculateDistance(posX, posY, rabbitX, rabbitY) < calculateDistance(oldPosX, oldPosY, rabbitX, rabbitY))) {
                        moved = true;
                        break;
                    } else {
                        moveUp();
                        checked[1] = 1;
                        break;
                    }
                case 2:
                    moveLeft();
                    if (isValidMove(posX, posY) && (calculateDistance(posX, posY, rabbitX, rabbitY) < calculateDistance(oldPosX, oldPosY, rabbitX, rabbitY))) {
                        moved = true;
                        break;
                    } else {
                        moveRight();
                        checked[2] = 1;
                        break;
                    }
                case 3:
                    moveRight();
                    if (isValidMove(posX, posY) && (calculateDistance(posX, posY, rabbitX, rabbitY) < calculateDistance(oldPosX, oldPosY, rabbitX, rabbitY))) {
                        moved = true;
                        break;
                    } else {
                        moveLeft();
                        checked[3] = 1;
                        break;
                    }
            }
            if(checked[0] == 1 && checked[1] == 1 && checked[2] == 1 && checked[3] == 1){
                break;
            }
        }
        if (posX == rabbitX && posY == rabbitY){
            field.eatenRabbits.set(i, 1);
            isChasing = false;
            chasedRabbit = -1;
            for(i = 0; i < field.farmers.size(); ++i){
                Dog otherDog = field.dogs.get(i);
                if(otherDog.isChasing && otherDog.chasedRabbit == this.chasedRabbit){
                    otherDog.isChasing = false;
                    otherDog.chasedRabbit = -1;
                }
            }
        }

    }

    public void move(){
        if(!isChasing){
            moveRandomly();
            lookForRabbits(posX,posY);
        } else {
            findRabbit(chasedRabbit);
        }
    }

}
