import java.util.ArrayList;
import java.util.Scanner;

public class Displayer {

    private static Displayer instance;
    private final Scanner scanner;

    private Displayer(){
        scanner = new Scanner(System.in);
    }

    public static Displayer getInstance(){
        if (instance == null){
            instance = new Displayer();
        }
        return instance;
    }

    public static void displayMainMenu(){
        System.out.println("Welcome to farmer visualisation.");
        System.out.println("1. Start simulation");
        System.out.println("2. Exit");
    }

    public int getChoice(int min, int max){
        int choice = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }

    public void displayField(Field f) {
        final String RABBIT = "🐇 ";
        final String FARMER = "🧑‍🌾 ";
        final String DOG = "🐕 ";
        final String CARROT = "🥕 ";
        final String GROWING_CARROT = "🌱 ";
        final String DAMAGED_LAND = "🌾 ";
        final String EMPTY_LAND = "🪹 ";

        String[][] view = new String[f.size][f.size];

        for (int i = 0; i < f.size; i++) {
            for (int j = 0; j < f.size; j++) {
                if (f.grid[i][j].isPlanted) {
                    if (f.grid[i][j].carrotGrowth >= 100) {
                        view[i][j] = CARROT;
                    } else {
                        view[i][j] = GROWING_CARROT;
                    }
                } else if (f.grid[i][j].isDamaged) {
                    view[i][j] = DAMAGED_LAND;
                } else {
                    view[i][j] = EMPTY_LAND;
                }
            }
        }

        for (Rabbit rabbit : f.rabbits) {
            view[rabbit.posY][rabbit.posX] = RABBIT;
        }
        for (Dog dog : f.dogs) {
            view[dog.posY][dog.posX] = DOG;
        }
        for (Farmer farmer : f.farmers) {
            view[farmer.posY][farmer.posX] = FARMER;
        }

        int size = view.length;

        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(view[i][j]);
            }
            System.out.println();
        }
    }

}
