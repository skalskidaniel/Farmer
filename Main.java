public class Main {

    public static void main(String[] args) {
        final int MAX_FIELD_SIZE = 50;
        Displayer displayer = Displayer.getInstance();

        Displayer.displayMainMenu();
        int choice = displayer.getChoice(1, 2);
        if (choice == 2){
            System.exit(0);
        }

        System.out.print("Enter the size of a field (max = " + MAX_FIELD_SIZE + "): ");
        int sizeOfField = displayer.getChoice(1, MAX_FIELD_SIZE);

        System.out.print("Enter the number of farmers (no more than " + sizeOfField + " squared): ");
        int numOfFarmers = displayer.getChoice(0, sizeOfField * sizeOfField);

        System.out.print("Enter the number of rabbits (no more than " + sizeOfField + " squared): ");
        int numOfRabbits = displayer.getChoice(0, sizeOfField * sizeOfField);

        Field f = new Field(sizeOfField, numOfFarmers, numOfRabbits);
        displayer.displayField(f);
    }
}
