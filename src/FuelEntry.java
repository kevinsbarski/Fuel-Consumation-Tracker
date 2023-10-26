import java.time.LocalDate;

public class FuelEntry {
    private double liters;
    private double distance;
    private double kmPerLiter;
    private LocalDate date;

    public FuelEntry(double liters, double distance, double kmPerLiter, LocalDate date) {
        this.liters = liters;
        this.distance = distance;
        this.kmPerLiter = kmPerLiter;
        this.date = date;
    }

    public double getLiters() {
        return liters;
    }

    public double getDistance() {
        return distance;
    }

    public double getKmPerLiter() {
        return kmPerLiter;
    }

    public LocalDate getDate() {
        return date;
    }
}
