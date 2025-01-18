public class Tile {
    public String state;
    public int carrotGrowth; // as percentage, if 100 then fully grown, can be eaten
    private boolean isPlanted = false;
    private boolean isDamaged = false;


    public void makeDamage(){
        // TODO method would rather be in rabbit class
    }

    public void repair(){
        // TODO method would rather be in farmer class
    }

    public void growCarrot(){
        /* grows carrot by 25 % **/
        if (carrotGrowth < 100){
            carrotGrowth += 25;
        }
    }
}
