package batsignal;

import java.io.IOException;

import twitter4j.TwitterException;

public class Main {
	
	public static void main(String args[]) throws TwitterException, IOException, InterruptedException
	{		
		SingleByteCom 	singleByteCom 	= new SingleByteCom();
		TweetChecker 	tweetChecker 	= new TweetChecker();
		App 			app 			= new App(tweetChecker);		
		UI 				ui 				= new UI(app);		
		
		app.addObserver(singleByteCom);						
		app.addObserver(ui);	
		
		singleByteCom.start();				
		app.start();		
		ui.start();	///stops when hitting return
		
		singleByteCom.close();
	}			
}
