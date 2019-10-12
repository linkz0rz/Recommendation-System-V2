
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    //private ArrayList<Rating> myRatings;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        //myRatings = new ArrayList<Rating>();
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        //for(int k=0; k < myRatings.size(); k++){
        //    if (myRatings.get(k).getItem().equals(item)){
        //        return true;
        //    }
        //}
        if (myRatings.containsKey(item) && myRatings.get(item).getItem().equals(item)){
            return true;
        }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        //for(int k=0; k < myRatings.size(); k++){
        //    if (myRatings.get(k).getItem().equals(item)){
        //        return myRatings.get(k).getValue();
        //    }
        //}
        if (myRatings.containsKey(item) && myRatings.get(item).getItem().equals(item)){
            return myRatings.get(item).getValue();
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
}
