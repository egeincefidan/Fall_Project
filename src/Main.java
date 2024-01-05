package src;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        long totalStartTime = System.nanoTime();

        // Parse cities from CSV
        List<City> cities = CSVParser.parseCities("data/cities.csv"); // Replace with your cities CSV file

        // Parse songs from CSV
        List<Song> songs = Knapsack.parseSongs("data/songs_data.csv"); // Replace with your songs CSV file

        // Solve Traveling Salesman Problem (TSP) to find the route of cities
        long startTime = System.nanoTime();
        List<City> optimalRoute = TSP.nearestNeighborTSP(cities); // Replace with your TSP solver
        long endTime = System.nanoTime();
    long tspDurationNano = endTime - startTime;
    double tspDurationSeconds = tspDurationNano / 1_000_000_000.0;
    System.out.println("TSP Algoritması Süresi: " + tspDurationNano + " nanosaniye (" + tspDurationSeconds + " saniye)");


        // Knapsack problem to select songs for each concert
        startTime = System.nanoTime();
        Map<City, List<Song>> cityConcerts = new HashMap<>();
        for (City city : optimalRoute) {
            List<Song> concertSongs = Knapsack.selectSongsForConcert(songs, city.getConcertDuration());
            cityConcerts.put(city, concertSongs);
        }
        endTime = System.nanoTime();
    long knapsackDurationNano = endTime - startTime;
    double knapsackDurationSeconds = knapsackDurationNano / 1_000_000_000.0;
    System.out.println("Knapsack Algoritması Süresi: " + knapsackDurationNano + " nanosaniye (" + knapsackDurationSeconds + " saniye)");

    long totalEndTime = System.nanoTime();
    long totalDurationNano = totalEndTime - totalStartTime;
    double totalDurationSeconds = totalDurationNano / 1_000_000_000.0;
    System.out.println("Toplam Çalışma Süresi: " + totalDurationNano + " nanosaniye (" + totalDurationSeconds + " saniye)");



        // Print the route and the concert lists for each city
        System.out.println("Optimal Concert Tour Route:");
        for (City city : optimalRoute) {
        System.out.println(city.getName() + " - Latitude: " + city.getX() + ", Longitude: " + city.getY() + ", Concert Duration: " + city.getConcertDuration() + " mins");
    }


        System.out.println("\nConcert Lists for Each City:");
    for (Map.Entry<City, List<Song>> entry : cityConcerts.entrySet()) {
        City city = entry.getKey();
        List<Song> concertSongs = entry.getValue();
        System.out.println("Concert in " + city.getName() + " (" + city.getConcertDuration() + " mins):");
        for (Song song : concertSongs) {
            System.out.println(song.getName() + " - Popularity: " + song.popularity + " - Duration: " + song.duration);
        }
        System.out.println();
    }
    }
}
