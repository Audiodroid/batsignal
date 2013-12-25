package batsignal;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UI implements Observer {

	private App app_ = null;
	
	public UI(App app) {
		app_ = app;
	}
	
	public void start() throws IOException, InterruptedException {
		
		waitForRETURN();
		
		app_.stop();
	}
	
	public void waitForRETURN() throws IOException, InterruptedException
	{
		int i = 0;		
		while(i == 0)
		{
			i = System.in.read();
			Thread.sleep(100);
		}	
	}

	@Override
	public void update(Observable obs, Object x) {
		
		Date now = new Date();		
		System.out.println("On " + now + ":\n");		
		
		@SuppressWarnings("unchecked")
		List<BatMessage> lsBatMsgs = (List<BatMessage>) (x);		
		for(BatMessage batMsg : lsBatMsgs)
			System.out.println(batMsg);
	}
}
