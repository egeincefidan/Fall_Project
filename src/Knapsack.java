package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knapsack {

    public static List<Song> parseSongs(String csvFile){
        List<Song> songs = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        boolean skipHeader = true; 
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue; 
                }
                String[] data = line.split(csvSplitBy);
                String name = data[0];
                double popularity = Double.parseDouble(data[1]);
                double duration = Double.parseDouble(data[2]);
                songs.add(new Song(name, popularity, duration));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public static List<Song> selectSongsForConcert(List<Song> allSongs, double concertDuration) {
        List<Song> selectedSongs = new ArrayList<>();
        double currentDuration = 0.0;
    
        allSongs.sort((s1, s2) -> Double.compare(s2.popularity, s1.popularity));
    
        for (Song song : allSongs) {
            if (currentDuration + song.duration <= concertDuration) {
                selectedSongs.add(song);
                currentDuration += song.duration;
            }
        }
    
        return selectedSongs;
    }

    public static void main(String[] args) {
        List<Song> songs = parseSongs("songs_data.csv");
        String fileName = "data/cities.csv"; 
        List<City> cities = CSVParser.parseCities(fileName);
        Map<City, List<Song>> result = new HashMap<>();

    for (City city : cities) {
        double concertDuration = city.getConcertDuration();
        List<Song> concertSongs = selectSongsForConcert(songs, concertDuration);
        result.put(city, concertSongs);

        System.out.println("Concert in " + city.getName() + " (" + concertDuration + " mins):");
        for (Song song : concertSongs) {
            System.out.println("Popularity: " + song.popularity + " - Duration: " + song.duration);
        }
        System.out.println();
    }
    }
}
