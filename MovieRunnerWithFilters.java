
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings () {
        ThirdRatings tr = new ThirdRatings();
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatings(1);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + r.getItem());
        }
    }
}
