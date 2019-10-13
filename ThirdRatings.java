
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        // ratedmoviesfull.csv, ratings.csv
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        //this("ratedmoviesfull.csv", "ratings.csv");
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> moviesAvg = new ArrayList<Rating>();
        
        for (String mId : movies) {
            double avg = getAverageByID(mId, minimalRaters);
            if (avg != 0.0) {
                moviesAvg.add(new Rating(MovieDatabase.getTitle(mId), avg));
            }
        }
        
        return moviesAvg;
    }
}
