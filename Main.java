import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int MAX_FIELD_SIZE = 50;
        Displayer displayer = Displayer.getInstance();

        Displayer.displayMainMenu();
        int choice = displayer.getChoice(1, 2);
        if (choice == 2) {
            System.exit(0);
        }

        System.out.print("Enter the size of a field (max = " + MAX_FIELD_SIZE + "): ");
        int sizeOfField = displayer.getChoice(1, MAX_FIELD_SIZE);

        System.out.print("Enter the number of farmers (no more than " + sizeOfField + " squared): ");
        int numOfFarmers = displayer.getChoice(0, sizeOfField * sizeOfField);

        System.out.print("Enter the number of rabbits (no more than " + sizeOfField + " squared): ");
        int numOfRabbits = displayer.getChoice(0, sizeOfField * sizeOfField);

        Field f = new Field(sizeOfField, numOfFarmers, numOfRabbits);

        Object displayLock = new Object();

        Thread farmersThread = new Thread(() -> {
            while (true) {
                for (Farmer farmer : f.farmers) {
                    farmer.move();
                }
                synchronized (displayLock) {
                    displayer.displayField(f);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread dogsThread = new Thread(() -> {
            while (true) {
                for (Dog dog : f.dogs) {
                    dog.move();
                }
                synchronized (displayLock) {
                    displayer.displayField(f);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread rabbitsThread = new Thread(() -> {
            while (true) {
                for (Rabbit rabbit : f.rabbits) {
                    rabbit.move();
                }
                synchronized (displayLock) {
                    displayer.displayField(f);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread growThread = new Thread(() -> {
            while (true){
                for (int i = 0; i < sizeOfField; ++i){
                    for (int j = 0; j < sizeOfField; ++j){
                        if(f.grid[i][j].isPlanted){
                            f.grid[i][j].growCarrot();
                        }
                    }
                }
                synchronized (displayLock) {
                    displayer.displayField(f);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        // TODO after some time rabbits seem to get stuck and dont move anymore
        farmersThread.start();
        dogsThread.start();
        rabbitsThread.start();
        growThread.start();
    }
}