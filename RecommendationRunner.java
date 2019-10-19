
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate () {
        Filter genreFilter = new GenreFilter("Action");
        Filter yearFilter = new YearAfterFilter(2000);
        Filter directorsFilter = new DirectorsFilter("Ben Stiller,Michael Bay,John Woo,James Cameron,Paul W.S. Anderson,J.J. Abrams,Richard Donner");
        AllFilters af = new AllFilters();
        af.addFilter(genreFilter);
        af.addFilter(yearFilter);
        af.addFilter(directorsFilter);
        return MovieDatabase.filterBy(af);
    }
    
    public void printItems () {
        System.out.println(getItemsToRate().size());
    }
    
    public void printRecommendationsFor (String webRaterID) {
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> avgMovieRatings = fr.getSimilarRatings (webRaterID, 10, 1);
        
        if (avgMovieRatings.size() > 0) {
            System.out.println("<table>");
            System.out.println("<tr><td>Movie ID</td><td>Movie Title</td><td>Genre</td><td>Year</td><td>Avg Rating</td></tr>");
            for (Rating r : avgMovieRatings) {
                System.out.println("<tr><td>" + r.getItem() + "</td><td>" + MovieDatabase.getTitle(r.getItem()) + "</td><td>" + MovieDatabase.getGenres(r.getItem()) + "</td><td>" + MovieDatabase.getYear(r.getItem()) + "</td><td>" + r.getValue() + "</td></tr>");
            }
            System.out.println("</table>");
        } else {
            System.out.println("No movies to recommend.");
        }
    }

}
