import java.util.*;

public class Movie {
	
	private int rating;
	private String title;
	private String imdb;
	private String director;
	private ArrayList<String> actors = new ArrayList<String>();
	private boolean liked = false;
	private boolean disliked = false;
	
	public Movie(String ttl, String ir, String dir){
		rating = 50;
		title = ttl;
		imdb = ir;
		director = dir;
	}
	
	public void setLiked(boolean opinion){
		if(opinion){
			liked = true;
			disliked = false;
		}
		else{
			liked = false;
			disliked = true;
		}
	}
	
	/*return 1 for liked
	-1 for disliked
	0 for not voted
	*/
	public int getLiked(){
		if(liked){
			return 1;
		}
		else if(disliked){
			return -1;
		}
		else{
			return 0;
		}
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDirector() {
		return director;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImdb() {
		return imdb;
	}
	
	public void addActor(String actor){
		actors.add(actor);
	}
	
	public ArrayList<String> getActors() {
		return actors;
	}
}
