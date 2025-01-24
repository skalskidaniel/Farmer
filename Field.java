public class Field {
    /** [0,0] position is in the upper left corner **/
    public final int size;
    public Tile[][] grid;
    public Farmer[] farmers;
//    public Dog[] dogs;
//    public Rabbit[] rabbits;

    public Field(int size){
        this.size = size;
        this.grid = new Tile[size][size];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                grid[i][j] = new Tile();
            }
        }
    }
}
