package src;

public class Song {
        String name;
        double popularity;
        double duration;

        public Song(String name, double popularity, double duration) {
            this.name = name;
            this.popularity = popularity;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }
        
    }

