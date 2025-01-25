public class Rabbit extends Movable{

    Rabbit(int n){
        FIELD_SIZE = n;
        posX = randomGenerator.nextInt(n);
        posY = randomGenerator.nextInt(n);
    }

    private boolean isValidMove(int X, int Y){
        //TODO
        return false;
    }

    public void makeDamage(){
        // TODO
    }

    public void move(){
        while(true){
            int direction = randomGenerator.nextInt(4);
            boolean moved = false;
            switch (direction){
                case 0:
                    moveUp();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveDown();
                        break;
                    }
                case 1:
                    moveDown();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveUp();
                        break;
                    }
                case 2:
                    moveLeft();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveRight();
                        break;
                    }
                case 3:
                    moveRight();
                    if(isValidMove(posX, posY)){
                        moved = true;
                        break;
                    } else{
                        moveLeft();
                        break;
                    }
            }
            if(moved){
                break;
            }
        }
        field.grid[posX][posY].isDamaged = true;
    }
}
