import java.util.ArrayList;

public class Field {
    /** [0,0] position is in the upper left corner **/
    private static Field instance;
    public final int size;
    public Tile[][] grid;
    public ArrayList<Farmer> farmers;
    public ArrayList<Dog> dogs;
    public ArrayList<Rabbit> rabbits;
    public ArrayList<Integer> eatenRabbits;
    private final int numOfFarmers;
    private final int numOfRabbits;

    public static Field getInstance(){
        if (instance == null){
            throw new RuntimeException("Field instance not initialized");
        }
        return instance;
    }

    public Field(int sizeOfField, int numOfFarmers, int numOfRabbits){
        this.size = sizeOfField;
        this.grid = new Tile[sizeOfField][sizeOfField];
        this.farmers = new ArrayList<>();
        this.dogs = new ArrayList<>();
        this.eatenRabbits = new ArrayList<>();
        this.rabbits = new ArrayList<>();
        this.numOfFarmers = numOfFarmers;
        this.numOfRabbits = numOfRabbits;
        instance = this;
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                instance.grid[i][j] = new Tile();
            }
        }

        for (int i = 0; i < numOfRabbits; ++i){
            instance.rabbits.add(new Rabbit(size));
            instance.eatenRabbits.add(0);
        }

        for (int i = 0; i < numOfFarmers; ++i){
            instance.farmers.add(new Farmer(size));
        }
    }

    public boolean isEnded(){
        for(int i = 0; i < rabbits.size(); ++i){
            if(eatenRabbits.get(i) == 0){
                return false;
            }
        }
        return true;
    }
}
