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

    public void findRabbit(int i){
        chasedRabbit = i;
        isChasing = true;
        int[] rabbitPosition = field.rabbits.get(i).getPosition();
        int rabbitX = rabbitPosition[0];
        int rabbitY = rabbitPosition[1];
        if(calculateDistance(posX, posY, rabbitX, rabbitY) > 5){
            isChasing = false;
            chasedRabbit = -1;
            return;
        }
        boolean moved = false;
        int checked[] = {0,0,0,0};
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
    }

    public void move(){
        if(!isChasing){
            moveRandomly();
            lookForRabbits(posX,posY);
        } else{
            findRabbit(chasedRabbit);
        }
    }

}
