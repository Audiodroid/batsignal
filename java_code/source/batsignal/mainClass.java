package batsignal;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class mainClass {
	
	public static void main(String args[]) throws TwitterException
	{
	    // The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();

	    searchForTweets(twitter);
	}
	
	static void searchForTweets(Twitter twitter) throws TwitterException
	{	    
	    Query query = new Query("#batsignal");
	    
		QueryResult result = twitter.search(query);
		
		String strUsername = null;
	    for (Status status : result.getTweets()) {
	    	
	    	strUsername = status.getUser().getScreenName();
	    	
	        System.out.println(strUsername + " says: ");
	        System.out.println("\"" + status.getText() + "\"");
	        System.out.println();
	    }
	}
}
