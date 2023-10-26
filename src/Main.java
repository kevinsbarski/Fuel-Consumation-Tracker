import java.time.Month;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuelTracker fuelTracker = new FuelTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFuel Tracker Menu:");
            System.out.println("1. Add Entry");
            System.out.println("2. Calculate Total Average Km per Liter");
            System.out.println("3. Calculate Average Km per Liter for a Month");
            System.out.println("4. Display All Entries");
            System.out.println("5. Display Entries for a Specific Month");
            System.out.println("6. Exit");
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
                    double totalAverage = fuelTracker.calculateTotalAverageKmPerLiter();
                    System.out.println("Total Average Km per Liter: " + fuelTracker.decimalFormat.format(totalAverage));
                    break;
                case 3:
                    System.out.print("Enter Month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    Month inputMonth = Month.of(month);
                    double averageForMonth = fuelTracker.calculateAverageKmPerLiterForMonth(inputMonth, year);
                    System.out.println("Average Km per Liter for " + inputMonth.toString() + " " + year + ": " + fuelTracker.decimalFormat.format(averageForMonth));
                    break;
                case 4:
                    fuelTracker.displayAllEntries();
                    break;
                case 5:
                    System.out.print("Enter Month (1-12): ");
                    int displayMonth = scanner.nextInt();
                    System.out.print("Enter Year: ");
                    int displayYear = scanner.nextInt();
                    Month displayInputMonth = Month.of(displayMonth);
                    fuelTracker.displayEntriesForMonth(displayInputMonth, displayYear);
                    break;
                case 6:
                    System.out.println("Exiting Fuel Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
