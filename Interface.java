import java.util.Scanner;

public class Interface {
    private static Interface instance;
    private Scanner scanner;

    private Interface(){
        scanner = new Scanner(System.in);
    }

    public static Interface getInstance(){
        if (instance == null){
            instance = new Interface();
        }
        return instance;
    }

    public static void displayMainMenu(){
        System.out.println("Welcome to farmer visualisation.");

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
