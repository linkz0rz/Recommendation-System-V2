
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + RaterDatabase.size());
        
        ArrayList<Rating> avgMovieRatings = fr.getAverageRatings(35);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //minimal rater of 1, the year set to 1980, and the genre set to “Romance”
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        AllFilters al = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
         
        al.addFilter(yearFilter);
        al.addFilter(genreFilter);
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + RaterDatabase.size());
        
        ArrayList<Rating> avgMovieRatings = fr.getAverageRatingsByFilter(8,al);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    } 
    
    public void printSimilarRatings () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatings ("65", 20, 5);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
}
