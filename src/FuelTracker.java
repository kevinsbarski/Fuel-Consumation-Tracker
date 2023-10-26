import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FuelTracker {
    private List<FuelEntry> fuelEntries;
    protected DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
                if (parts.length >= 4) {
                    double liters = Double.parseDouble(parts[0]);
                    double distance = Double.parseDouble(parts[1]);
                    double kmPerLiter = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    FuelEntry entry = new FuelEntry(liters, distance, kmPerLiter, date);
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
                writer.println(
                        decimalFormat.format(entry.getLiters()) + "," +
                                decimalFormat.format(entry.getDistance()) + "," +
                                decimalFormat.format(entry.getKmPerLiter()) + "," +
                                entry.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                );
            }
        } catch (IOException ex) {
            System.out.println("Error writing data to the CSV file.");
        }
    }

    void addEntry(double liters, double distance) {
        double kmPerLiter = distance / liters;
        LocalDate date = LocalDate.now(); // Get the current date
        FuelEntry entry = new FuelEntry(liters, distance, kmPerLiter, date);
        fuelEntries.add(entry);
        saveEntriesToCSV();
        System.out.println("Entry added successfully.");
    }

    double calculateTotalAverageKmPerLiter() {
        int entryCount = fuelEntries.size();
        if (entryCount > 0) {
            double totalKmPerLiter = fuelEntries.stream().mapToDouble(FuelEntry::getKmPerLiter).sum();
            return totalKmPerLiter / entryCount;
        } else {
            return 0.0;
        }
    }

    double calculateAverageKmPerLiterForMonth(Month month, int year) {
        List<FuelEntry> entriesForMonth = fuelEntries.stream()
                .filter(entry -> entry.getDate().getMonth() == month && entry.getDate().getYear() == year)
                .collect(Collectors.toList());

        int entryCount = entriesForMonth.size();
        if (entryCount > 0) {
            double totalKmPerLiter = entriesForMonth.stream().mapToDouble(FuelEntry::getKmPerLiter).sum();
            return totalKmPerLiter / entryCount;
        } else {
            return 0.0;
        }
    }

    List<FuelEntry> getEntriesForMonth(Month month, int year) {
        return fuelEntries.stream()
                .filter(entry -> entry.getDate().getMonth() == month && entry.getDate().getYear() == year)
                .collect(Collectors.toList());
    }

    void displayAllEntries() {
        if (fuelEntries.isEmpty()) {
            System.out.println("No entries to display.");
        } else {
            System.out.println("All Entries:");
            for (FuelEntry entry : fuelEntries) {
                System.out.println("Date: " + entry.getDate() +
                        " | Liters: " + decimalFormat.format(entry.getLiters()) +
                        " | Distance: " + decimalFormat.format(entry.getDistance()) + " km" +
                        " | Km per Liter: " + decimalFormat.format(entry.getKmPerLiter()));
            }
        }
    }

    void displayEntriesForMonth(Month month, int year) {
        List<FuelEntry> entries = getEntriesForMonth(month, year);

        if (entries.isEmpty()) {
            System.out.println("No entries for " + month.toString() + " " + year + ".");
        } else {
            System.out.println("Entries for " + month.toString() + " " + year + ":");
            for (FuelEntry entry : entries) {
                System.out.println("Date: " + entry.getDate() +
                        " | Liters: " + decimalFormat.format(entry.getLiters()) +
                        " | Distance: " + decimalFormat.format(entry.getDistance()) + " km" +
                        " | Km per Liter: " + decimalFormat.format(entry.getKmPerLiter()));
            }
        }
    }

    // Private method for formatting date
    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
