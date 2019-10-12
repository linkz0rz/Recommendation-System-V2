
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies (String filename) {
        FileResource fr = new FileResource(filename);
        String file = fr.asString();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        CSVParser parser = fr.getCSVParser();//CSVParser.parse(file, CSVFormat.DEFAULT);
            
        for (CSVRecord cr : parser) {
            int theMinutes = Integer.parseInt(cr.get("minutes"));
            movies.add(new Movie(cr.get("id"),
                                 cr.get("title"),
                                 cr.get("year"),
                                 cr.get("genre"),
                                 cr.get("director"),
                                 cr.get("country"),
                                 cr.get("poster"),
                                 theMinutes)
                       );
        }
                
        return movies;
    }
    
    public int findGenre (ArrayList<Movie> movies, String genre) {
        int genreCount = 0;
        for (Movie m : movies) {
            if (m.getGenres().contains(genre)) {
                genreCount++;
            }
        }        
        return genreCount;
    }
    
    public int findMinutesGreater (ArrayList<Movie> movies, int minutes) {
        int movieCount = 0;
        for (Movie m : movies) {
            if (m.getMinutes() > minutes) {
                movieCount++;
            }
        }        
        return movieCount;
    }
    
    public void findDirectorMovieCounts (ArrayList<Movie> movies) {
        HashMap<String, Integer> directorCount = new HashMap<String, Integer>();        
        for (Movie m : movies) {
            if (directorCount.containsKey(m.getDirector())) {
                directorCount.put(m.getDirector(),directorCount.get(m.getDirector())+1);
            } else {
                directorCount.put(m.getDirector(),1);
            }
        }        
        
        String maxDirector = "";
        int maxDirected = 0;
        for (String director : directorCount.keySet()) {
        //    System.out.println(director + " " + directorCount.get(director));
            if (maxDirected < directorCount.get(director)) {
                maxDirected = directorCount.get(director);
                maxDirector = director;
            }
        }
        System.out.println("Director " + maxDirector + " directed the most movies with " + maxDirected);
    }
    
    public void testLoadMovies () {
        String file = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(file);        
        //for (Movie m : movies) {
        //    System.out.println(m);
        //}
        System.out.println("There are " + movies.size()  + " in file ratedmoviesfull.csv");
        System.out.println("Movies that are comedy genres: " + findGenre(movies,"Comedy"));
        System.out.println("Movies that have higher running times than 150 mins: " + findMinutesGreater(movies,150));
        findDirectorMovieCounts(movies);
    }
    
    public ArrayList<Rater> loadRaters (String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        
        FileResource fr = new FileResource(filename);
        String file = fr.asString();        
        CSVParser parser = fr.getCSVParser();
        
        HashMap<String, ArrayList<Rating>> raterIdList = new HashMap<String, ArrayList<Rating>>();
        
        for (CSVRecord cr : parser) {
            String raterId = cr.get("rater_id");
            if (!raterIdList.containsKey(raterId)) {
                Rating rating = new Rating(cr.get("movie_id"), Double.parseDouble(cr.get("rating")));
                ArrayList<Rating> arrRating = new ArrayList<Rating>();
                arrRating.add(rating);
                raterIdList.put(raterId, arrRating);
            } else {
                ArrayList<Rating> arrRating = raterIdList.get(raterId);
                arrRating.add(new Rating(cr.get("movie_id"), Double.parseDouble(cr.get("rating"))));
                raterIdList.put(raterId, arrRating);
            }
        }
        
        //for (String raterId : raterIdList.keySet()) {
        //    System.out.println("Rater ID: " + raterId + " - Ratings: " + raterIdList.get(raterId).size());
        //    for (Rating r : raterIdList.get(raterId)) {
        //        System.out.println("Movie ID: " + r.getItem() + ", Rating: " + r.getValue());
        //    }
        //}
        
        for (String raterId : raterIdList.keySet()) {
            ArrayList<Rating> rating = raterIdList.get(raterId);
            Rater rater = new EfficientRater(raterId);
            for (Rating r : raterIdList.get(raterId)) {
                rater.addRating(r.getItem(), r.getValue());
            }
            raters.add(rater);
        }
        
        return raters;
    }
    
    public void testLoadRaters () {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Number of raters: " + raters.size());
        int maxRatings = 0;
        int movieRatingCount = 0;
        HashSet<String> moviesRated = new HashSet<String>();
        
        for (Rater r : raters) {
            if (r.getID().equals("193")) {
                System.out.println("Rater ID: 193");
                System.out.println("Number of ratings: " + r.getItemsRated().size());
            }
            
            if (maxRatings < r.getItemsRated().size()) {
                maxRatings = r.getItemsRated().size();
            }
        }
        
        System.out.println("Max ratings: " + maxRatings);
        for (Rater r : raters) {
            if (r.numRatings() == maxRatings) {
                System.out.println("Rater ID " + r.getID() + " matches max ratings");
            }
        }
        
        for (Rater r : raters) {
            for (String s : r.getItemsRated()) {
                if (s.equals("1798709")) movieRatingCount++;                
            }
        }
        System.out.println("Ratings for movie: " + movieRatingCount);
        
        for (Rater r : raters) {
            for (String s : r.getItemsRated()) {
                if (!moviesRated.contains(s)) moviesRated.add(s);
            }
        }
        System.out.println("Number of movies rated: " + moviesRated.size());
    }
}
