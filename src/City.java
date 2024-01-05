package src;
public class City {
    private String name;
    private double latitude;
    private double longitude;
    private double concertDuration;

    public City(String name, double latitude, double longitude, double concertDuration) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.concertDuration = concertDuration;
    }
    public double distanceTo(City otherCity) {
        double xDistance = Math.abs(this.latitude - otherCity.getX());
        double yDistance = Math.abs(this.longitude - otherCity.getY());
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return latitude;
    }

    public double getY() {
        return longitude;
    }
    public double getConcertDuration() {
        return concertDuration;
    }
}
