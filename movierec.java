/*Information courtesy of
IMDb
(http://www.imdb.com).
Used with permission.*/


import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class movierec {
	
	static ArrayList<Movie> array_rank = new ArrayList<Movie>();
	static Hashtable<String, ArrayList<Movie>> director_tb = new Hashtable<String, ArrayList<Movie>>();
	
    public static void main(String[] args) throws Exception {
    	
    	//Website
        Document document = Jsoup.connect("http://www.imdb.com/chart/top").get();
        
        
        
        //get title, rating, url
        for(Element item: document.select("table.chart.full-width tr")){
        	String title = item.select(".titleColumn a").text();
        	String rating = item.select(".imdbRating").text();
        	
        	if(title.length()>0){
            	String urls = item.select(".titleColumn a").attr("href");
            	String people = item.select(".titleColumn a").attr("title");
            	String director = people.substring(0,people.indexOf(" (dir.)"));
            	
            	//add Movie item to heap
            	array_rank.add( new Movie(title, rating, director));
            	
            	String dirname = array_rank.get(array_rank.size()-1).getDirector();
            	
            	if (director_tb.containsKey(dirname)) {
            		director_tb.get(dirname).add(array_rank.get(array_rank.size()-1));
            	}
            	else{
            		director_tb.put(dirname, new ArrayList<Movie>());
            		director_tb.get(dirname).add(array_rank.get(array_rank.size()-1));
            	}
            	
            	/*
           		System.out.println(title + " -> Rating: " + rating);
            	System.out.println(director);
            	System.out.println("http://www.imdb.com"+urls);*/
        	}
        }
        for(int i = 0; i<array_rank.size();i++){
        	array_rank.get(i).setRating(array_rank.size()-i);
        }
        
        quicksort(0,array_rank.size()-1);
        
        for(int i = 0; i<array_rank.size();i++){
       		System.out.println(array_rank.get(i).getTitle() + " -> Rating: " + array_rank.get(i).getRating());
        }
        
    }
    
    public static void rateMovie(Movie m, int points){
    	String dirname = m.getDirector();
    	ArrayList<Movie> dirm = director_tb.get(dirname);
    	for(int i =0; i<dirm.size();i++){
    		dirm.get(i).setRating(dirm.get(i).getRating()+points);
    	}
    }
    
    
    //Quicksort looks efficient for this. Highest values will be arraylist's right end
    public static void quicksort(int bottom, int top){
        if (top-bottom > 1){
	    	int pivot = array_rank.get(top).getRating(); 
	        int mid = bottom;
	        for( int i = bottom; i<top;i++){
	        	Movie temp =  array_rank.get(i);
	        	if (temp.getRating() < pivot){
	        		swap(mid,i);
	        		mid++;
	        	}
	        }
	        swap(mid,top);
	    	quicksort(bottom, mid-1);
	    	quicksort(mid+1,top);
        }
    }

    //Swap 2 elements by index
    public static void swap(int first, int second){
    	Movie temp = array_rank.get(first);
    	array_rank.set(first, array_rank.get(second));
    	array_rank.set(second, temp);
    }
    
 /*   too long. 
    public void heapstep(int index){
    	int left = index*2;
    	int right = index*2+1;
    	
    	if(heap.get(right).getRating()<=heap.get(left).getRating()){
    		if(heap.get(index).getRating()<heap.get(left).getRating()){
    			swap(left,index);
    			heapstep(left);
    		}
    	}
    	else{
    		if(heap.get(index).getRating()<heap.get(right).getRating()){
        		swap(right,index);
        		heapstep(right);
    		}
    	}
    }
    

   */ 
}