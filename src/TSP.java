package src;
import java.util.ArrayList;
import java.util.List;


public class TSP {
    

    public static List<City> nearestNeighborTSP(List<City> cities) {
        int numCities = cities.size();
        boolean[] visited = new boolean[numCities];
        List<City> tour = new ArrayList<>();

        City currentCity = cities.get(0); // Start from the first city
        visited[0] = true;
        tour.add(currentCity);

    while (tour.size() < numCities) {
        City nearest = null;
        double nearestDistance = Double.MAX_VALUE;

        for (int i = 0; i < numCities; i++) {
            if (!visited[i]) {
                double distance = currentCity.distanceTo(cities.get(i));
                if (distance < nearestDistance) {
                    nearest = cities.get(i);
                    nearestDistance = distance;
                }
            }
        }

        tour.add(nearest);
        visited[cities.indexOf(nearest)] = true;
        currentCity = nearest;
    }
        return tour;
    }

    

    public static void main(String[] args) {
        // Use CSVParser to obtain the list of cities from the CSV file
        String fileName = "data/cities.csv"; // Provide the correct path to your CSV file

        List<City> cities = CSVParser.parseCities(fileName);
        
        List<City> optimalRoute = TSP.nearestNeighborTSP(cities);

    }
}

