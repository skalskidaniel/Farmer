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
}
