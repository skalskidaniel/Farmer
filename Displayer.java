import java.util.ArrayList;
import java.util.Scanner;

public class Displayer {

    private static Displayer instance;
    private Scanner scanner;

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

    public void displayField(Field f){
        // TODO display grid, find farmers and animals, print them on top

//        🐇 - rabbit
//        🧑‍🌾 - farmer
//        🐕 - dog
//        🥕 - carrot
//        🌾 - damaged land
//        🌱 - growing carrot
//        🪹 - empty land

        ArrayList<String> view = new ArrayList<>();

        // construct a view of field
        // TODO display actual item from grid
        String horizontalBorder = " " + "-".repeat(f.size * 2) + " ";
        view.add(horizontalBorder);
        for (int i = 0; i < f.size; ++i){
            String row = "| ";
            for (int j = 0; j < f.size; ++j) {
                row += "." + " ";
            }
            row += "|";
            view.add(row);
        }
        view.add(horizontalBorder);

        // TODO add all species to a view
        for (Rabbit r : f.rabbits){
            StringBuilder sb = new StringBuilder(view.get(r.posY));
            sb.setCharAt(r.posX, 🐇);
            view.set(r.posY, sb.toString());
        }

        // print view
        for (String row : view){
            System.out.println(row);
        }
    }
}
