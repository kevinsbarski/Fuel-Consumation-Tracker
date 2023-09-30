import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuelTracker fuelTracker = new FuelTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFuel Tracker Menu:");
            System.out.println("1. Add Entry");
            System.out.println("2. Calculate Average Km per Liter");
            System.out.println("3. Display All Entries");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Liters of Fuel: ");
                    double liters = scanner.nextDouble();
                    System.out.print("Enter Distance (in km): ");
                    double distance = scanner.nextDouble();
                    fuelTracker.addEntry(liters, distance);
                    break;
                case 2:
                    fuelTracker.calculateAverageKmPerLiter();
                    break;
                case 3:
                    fuelTracker.displayAllEntries();
                    break;
                case 4:
                    System.out.println("Exiting Fuel Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
