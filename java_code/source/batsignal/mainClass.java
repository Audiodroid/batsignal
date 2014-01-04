package batsignal;

import java.io.IOException;

import twitter4j.TwitterException;

public class mainClass {
	
	public static void main(String args[]) throws TwitterException, IOException, InterruptedException
	{		
		TweetChecker tweetChecker = new TweetChecker();
		App app = new App(tweetChecker);
		
		SingleByteCommunication singleByteCom = new SingleByteCommunication();		
		singleByteCom.init();				
		app.addObserver(singleByteCom);
		
		UI ui = new UI(app);		
		app.addObserver(ui);	
		
		app.start();		
		ui.start();	
		
		singleByteCom.close();
	}			
}
