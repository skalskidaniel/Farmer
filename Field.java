import java.util.ArrayList;
import java.util.Random;

public class Field {
    /** [0,0] position is in the upper left corner **/
    public final int size;
    public Tile[][] grid;
    public ArrayList<Farmer> farmers;
    public ArrayList<Dog> dogs;
    public ArrayList<Rabbit> rabbits;
    private final Random randomGenerator = new Random();

    public Field(int sizeOfField, int numOfFarmers, int numOfRabbits){
        this.size = sizeOfField;
        this.grid = new Tile[sizeOfField][sizeOfField];
        initializeGrid();

        for (int i = 0; i < numOfFarmers; ++i){
            farmers.add(new Farmer(sizeOfField));
            dogs.add(new Dog(sizeOfField));
        }

        for (int i = 0; i < numOfRabbits; ++i){
            rabbits.add(new Rabbit(sizeOfField));
        }
    }

    private void initializeGrid() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                grid[i][j] = new Tile();
            }
        }
    }
}
