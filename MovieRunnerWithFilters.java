
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
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatings(35);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear () {
        ThirdRatings tr = new ThirdRatings();
        Filter yearFilter = new YearAfterFilter(2000);
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(20,yearFilter);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre () {
        ThirdRatings tr = new ThirdRatings();
        Filter genreFilter = new GenreFilter("Comedy");
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(20,genreFilter);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings();
        Filter minutesFilter = new MinutesFilter(105, 135);
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(5,minutesFilter);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings();
        Filter directorFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(4,directorFilter);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Director: " + MovieDatabase.getDirector(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        //minimal rater of 1, the year set to 1980, and the genre set to “Romance”
        ThirdRatings tr = new ThirdRatings();
        AllFilters al = new AllFilters();
        Filter yearFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
         
        al.addFilter(yearFilter);
        al.addFilter(genreFilter);
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(8,al);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Genre: " + MovieDatabase.getGenres(r.getItem()) + " " + 
                               MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    } 
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        //minimal rater of 1, minimum minutes set to 30, maximum minutes set to 170, and the directors set to "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"”
        ThirdRatings tr = new ThirdRatings();
        AllFilters al = new AllFilters();
        Filter minutesFilter = new MinutesFilter(90,180);
        Filter directorFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
         
        al.addFilter(minutesFilter);
        al.addFilter(directorFilter);
        
        System.out.println("Movies count: " + MovieDatabase.size());
        System.out.println("Raters count: " + tr.getRaterSize());
        
        ArrayList<Rating> avgMovieRatings = tr.getAverageRatingsByFilter(3,al);
        System.out.println("Found movies: " + avgMovieRatings.size());
        Collections.sort(avgMovieRatings);
        
        for (Rating r : avgMovieRatings) {
            System.out.println(r.getValue() + " Minutes: " + MovieDatabase.getMinutes(r.getItem()) + " " + 
                               MovieDatabase.getDirector(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    } 
}
