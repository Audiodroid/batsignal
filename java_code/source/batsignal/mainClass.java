package batsignal;

import java.io.IOException;

import twitter4j.TwitterException;

public class mainClass {
	
	public static void main(String args[]) throws TwitterException, IOException, InterruptedException
	{		
		App app = new App();		
		UI ui = new UI(app);
		
		app.addObserver(ui);	

		app.start();		
		ui.start();		
	}			
}
