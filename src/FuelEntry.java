public class FuelEntry {
    private double liters;
    private double distance;
    private double kmPerLiter;
    public FuelEntry(double liters, double distance, double kmPerLiter) {
        this.liters = liters;
        this.distance = distance;
        this.kmPerLiter = kmPerLiter;
    }
    public double getLiters() { return liters; }
    public double getDistance() { return distance; }
    public double getKmPerLiter() { return kmPerLiter; }
}
