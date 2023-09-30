import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FuelTracker {
    private List<FuelEntry> fuelEntries;
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private String csvFilePath = "fuel_data.csv";

    public FuelTracker() {
        fuelEntries = new ArrayList<>();
        loadEntriesFromCSV();
    }

    private void loadEntriesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    double liters = Double.parseDouble(parts[0]);
                    double distance = Double.parseDouble(parts[1]);
                    double kmPerLiter = Double.parseDouble(parts[2]);
                    FuelEntry entry = new FuelEntry(liters, distance, kmPerLiter);
                    fuelEntries.add(entry);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error reading data from the CSV file.");
        }
    }

    private void saveEntriesToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            for (FuelEntry entry : fuelEntries) {
                writer.println(decimalFormat.format(entry.getLiters()) + "," +
                        decimalFormat.format(entry.getDistance()) + "," +
                        decimalFormat.format(entry.getKmPerLiter()));
            }
        } catch (IOException ex) {
            System.out.println("Error writing data to the CSV file.");
        }
    }
    void addEntry(double liters, double distance) {
        double kmPerLiter = distance / liters;
        FuelEntry entry = new FuelEntry(liters, distance, kmPerLiter);
        fuelEntries.add(entry);
        saveEntriesToCSV();
        System.out.println("Entry added successfully.");
    }
    void calculateAverageKmPerLiter() {
        int trackCount = fuelEntries.size();
        if (trackCount > 0) {
            double totalKmPerLiter = 0.0;
            for (FuelEntry entry : fuelEntries) {
                totalKmPerLiter += entry.getKmPerLiter();
            }
            double averageKmPerLiter = totalKmPerLiter / trackCount;
            System.out.println("Average Km per Liter: " + decimalFormat.format(averageKmPerLiter));
        } else {
            System.out.println("No data available to calculate average.");
        }
    }
    void displayAllEntries() {
        if (fuelEntries.isEmpty()) {
            System.out.println("No entries to display.");
        } else {
            System.out.println("All Entries:");
            for (FuelEntry entry : fuelEntries) {
                System.out.println("Liters: " + decimalFormat.format(entry.getLiters()) +
                        " | Distance: " + decimalFormat.format(entry.getDistance()) + " km" +
                        " | Km per Liter: " + decimalFormat.format(entry.getKmPerLiter()));
            }
        }
    }

}
