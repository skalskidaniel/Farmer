public class Field {
    private final int size;
    public Tile[][] grid;

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
