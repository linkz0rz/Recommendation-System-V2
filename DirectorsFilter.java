
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
	private String myDirector;
	
	public DirectorsFilter(String directors) {
		myDirector = directors;
	}
	
	@Override
	public boolean satisfies(String id) {
	        String[] directors = myDirector.split(",");
	        for (int i = 0; i < directors.length; i++) {
	            if (MovieDatabase.getDirector(id).trim().equals(directors[i].trim()))
	                return true;
	        }
		return false;
	}

}