public class Main {

    public static void main(String[] args) {
        final int MAX_FIELD_SIZE = 50;
        Displayer displayer = Displayer.getInstance();

        Displayer.displayMainMenu();
        int choice = displayer.getChoice(1, 3);
        if (choice == 3) {
            System.exit(0);
        }
        else if (choice == 2){
            displayer.displayHistory();
            System.exit(0);
        }

        System.out.print("Enter the size of a field (max = " + MAX_FIELD_SIZE + "): ");
        int sizeOfField = displayer.getChoice(1, MAX_FIELD_SIZE);

        System.out.print("Enter the number of farmers (at least one, no more than " + sizeOfField + "): ");
        int numOfFarmers = displayer.getChoice(1, sizeOfField);

        System.out.print("Enter the number of rabbits (no more than " + sizeOfField + "): ");
        int numOfRabbits = displayer.getChoice(0, sizeOfField);

        Field f = new Field(sizeOfField, numOfFarmers, numOfRabbits);

        Object displayLock = new Object();

        Thread farmersThread = new Thread(() -> {
            while (!f.isEnded()) {
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
            while (!f.isEnded()) {
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
            while (!f.isEnded()) {
                for (Rabbit rabbit : f.rabbits) {
                    if(f.eatenRabbits.get(f.rabbits.indexOf(rabbit)) == 0){
                        rabbit.move();
                    }
                }
                synchronized (displayLock) {
                    if(f.eatenRabbits.get(f.rabbits.indexOf(f.rabbits.get(0))) == 0){
                        displayer.displayField(f);
                    }
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
            while (!f.isEnded()) {
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

        long startTime = System.currentTimeMillis();

        farmersThread.start();
        dogsThread.start();
        rabbitsThread.start();
        growThread.start();

        try {
            farmersThread.join();
            dogsThread.join();
            rabbitsThread.join();
            growThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime) / 1000;

        displayer.displayEndMessage(duration);

        DatabaseManager databaseManager = new DatabaseManager(sizeOfField, numOfFarmers, numOfRabbits, duration);
        databaseManager.saveSimulationData();
    }
}