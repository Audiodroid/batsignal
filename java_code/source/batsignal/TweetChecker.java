package batsignal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetChecker {

	Twitter twitter_ = null;
	
	public TweetChecker()
	{
		// The factory instance is re-useable and thread safe.
	    twitter_ = TwitterFactory.getSingleton();
	}
	
	@SuppressWarnings("deprecation")
	public List<BatMessage> check() throws TwitterException
	{
		List<BatMessage> lsBatMsgs = new ArrayList<BatMessage>();
    	
	    Query query = new Query("#batsignal");
	    
		QueryResult result = twitter_.search(query);
		
		Date date = new Date();
		
		int today = date.getDay();
		
    	BatMessage batMsg = null;	
    	for (Status status : result.getTweets()) {
	    	    		
    		if(status.getCreatedAt().getDay() != today)
    			continue;
    		
    		batMsg = new BatMessage(
		    	status.getUser().getScreenName(),
		    	status.getCreatedAt(),
		    	status.getText()
    		);
	    	
	    	lsBatMsgs.add(batMsg);	    	
	    }
    	
    	return lsBatMsgs;
	}	
}
