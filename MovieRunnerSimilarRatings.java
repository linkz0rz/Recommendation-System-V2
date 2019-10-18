
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
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatings ("71", 20, 5);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
    
    public void printSimilarRatingsByGenre () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        Filter genreFilter = new GenreFilter("Mystery");
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatingsByFilter ("964", 20, 5, genreFilter);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
    
    public void printSimilarRatingsByDirector () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        Filter directorFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatingsByFilter ("120", 10, 2, directorFilter);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Director: " + MovieDatabase.getDirector(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        AllFilters al = new AllFilters();
        Filter genreFilter = new GenreFilter("Drama");
        Filter minutesFilter = new MinutesFilter(80, 160);
        al.addFilter(genreFilter);
        al.addFilter(minutesFilter);
        
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatingsByFilter ("168", 10, 3, al);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " Minutes: " 
                               + MovieDatabase.getMinutes(r.getItem()) + " " +
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        AllFilters al = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1975);
        Filter minutesFilter = new MinutesFilter(70, 200);
        al.addFilter(yearFilter);
        al.addFilter(minutesFilter);
        
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatingsByFilter ("314", 10, 5, al);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Year: " + MovieDatabase.getYear(r.getItem()) + " Minutes: " 
                               + MovieDatabase.getMinutes(r.getItem()) + " " +
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
        
    }
}
