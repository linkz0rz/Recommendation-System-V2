
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    
    private double dotProduct(Rater me, Rater r) {
        double sum = 0.0;
        for (String movieRated : me.getItemsRated()) {
            if (r.hasRating(movieRated)) {
                double myRating = me.getRating(movieRated)-5;
                double theirRating = r.getRating(movieRated)-5;
                double ratingProduct = myRating*theirRating;
                sum += ratingProduct;
            }
        }
        
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double comparison = dotProduct(RaterDatabase.getRater(id), r);
                ratings.add(new Rating(r.getID(), comparison));
            }
        }
        Collections.sort(ratings,Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieSimilarRatings = new ArrayList<Rating>();
	ArrayList<Rating> raterSimilarList = getSimilarities(id);
	ArrayList<String> movieIDList = new ArrayList<String>();

	HashMap<String,Double> similarMap = new HashMap<String,Double>();
	int minIndex = Math.min(getSimilarities(id).size(), numSimilarRaters);

	for (Rating similar : getSimilarities(id).subList(0, minIndex)) {
	    if (similar.getValue() > 0) {
	        similarMap.put(similar.getItem(), similar.getValue());
	    }
	}

        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
	    int count = 0;
	    double total = 0;

	    for (Rater rater : RaterDatabase.getRaters()) {
	        double rating = -1;
		if (similarMap.containsKey(rater.getID()) && rater.hasRating(movieID)) {
		    rating = rater.getRating(movieID) * similarMap.get(rater.getID());
		}
                if (rating == -1) {
                } else {
		    count++;
		    total = total + rating;
		}
	    }

	    if (count < minimalRaters || total == 0) {
	    } else {
                movieSimilarRatings.add(new Rating(movieID, total/count));
	    }

	}
        Collections.sort(movieSimilarRatings, Collections.reverseOrder());
        return movieSimilarRatings;
    }
    
    private double getAverageByIDSimilar (String id, ArrayList<Rater> raters, int minimalRaters) {
        int ratersCount = 0;
        ArrayList<Double> ratingsList = new ArrayList<Double>();
        for (Rater r : raters) {
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
       
    private double getAverageByID (String id, int minimalRaters) {
        int ratersCount = 0;
        ArrayList<Double> ratingsList = new ArrayList<Double>();
        for (Rater r : RaterDatabase.getRaters()) {
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
                moviesAvg.add(new Rating(mId, avg));
            }
        }
        
        return moviesAvg;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> moviesAvg = new ArrayList<Rating>();
        
        for (String mId : movies) {
            double avg = getAverageByID(mId, minimalRaters);
            if (avg != 0.0) {
                moviesAvg.add(new Rating(mId, avg));
            }
        }
        
        return moviesAvg;
    }
}
