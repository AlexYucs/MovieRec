/*Information courtesy of
IMDb
(http://www.imdb.com).
Used with permission.*/


import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class movierec {
	
	static ArrayList<Movie> heap = new ArrayList<Movie>();
	
    public static void main(String[] args) throws Exception {
    	
    	//Website
        Document document = Jsoup.connect("http://www.imdb.com/chart/top").get();
        
        //get title, rating, url
        for(Element item: document.select("table.chart.full-width tr")){
        	String title = item.select(".titleColumn a").text();
        	String rating = item.select(".imdbRating").text();
        	
        	if(title.length()>0){
        		System.out.println(title + " -> Rating: " + rating);
            	String urls = item.select(".titleColumn a").attr("href");
            	String people = item.select(".titleColumn a").attr("title");
            	String director = people.substring(0,people.indexOf(" (dir.)"));
            	
            	heap.add( new Movie(title, rating, director));
            	
            	System.out.println(director);
            	System.out.println("http://www.imdb.com"+urls);
        	}
        }  
    }
    
    public void heapify(){
    	
    }
    
    public void swap(){

    }
    
}