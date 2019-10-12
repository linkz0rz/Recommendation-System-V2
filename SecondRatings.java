
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        // ratedmoviesfull.csv, ratings.csv
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        //this("ratedmoviesfull.csv", "ratings.csv");
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters) {
        int ratersCount = 0;
        ArrayList<Double> ratingsList = new ArrayList<Double>();
        for (Rater r : myRaters) {
            if (r.getRating(id) != -1) {
                ratersCount++;
                ratingsList.add(r.getRating(id));
            }
        }
        if (ratersCount >= minimalRaters) {
            double ratingsSum = 0.0;
            for (double rating : ratingsList) {
                ratingsSum += rating;
            }
            return ratingsSum/ratingsList.size();
        }
        
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> moviesAvg = new ArrayList<Rating>();
        
        for (Movie m : myMovies) {
            double avg = getAverageByID(m.getID(), minimalRaters);
            if (avg != 0.0) {
                // should be loading in the movie name not movie id
                moviesAvg.add(new Rating(getTitle(m.getID()), avg));
            }
        }
        
        return moviesAvg;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }        
        return "NO SUCH ID.";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }        
        return "NO SUCH TITLE.";
    }
}
