
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings () {
        SecondRatings sr = new SecondRatings();
        
        System.out.println("Movies count: " + sr.getMovieSize());
        System.out.println("Raters count: " + sr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = sr.getAverageRatings(12);
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + r.getItem());
        }
    }
    
    public void getAverageRatingOneMovie () {
        SecondRatings sr = new SecondRatings();
        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(0);
        for (Rating r : avgRatings) {
            if (r.getItem().equals("Vacation")){
                System.out.println("Average for movie: " + r.getValue());
            }
        }
        
    }
    
    public void flex () {
        double ans = ((double)8+6+7)/3;
        System.out.println(ans);
    }
}
