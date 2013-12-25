package batsignal;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import twitter4j.TwitterException;

class App extends Observable implements Runnable {
	
	private final long SECOND = 1000;
//	private final long MINUTE = 60 * SECOND;
	private final long MS = 1;
	
	private final long intervalTime_ = 90 * SECOND;
	private final long sleepTime_ = 100 * MS;
	
	private volatile boolean stop_ = false;
	private volatile boolean stopped_ = false;
	
	private long lastTime_ = 0;
	private long nowTime_ = 0;

	private Thread 			thread_ 	 = null;		
	private TweetChecker 	tweetChecker = new TweetChecker();
	
	@Override
	public void run() 
	{
		try {
							
			lastTime_ = 0;
			
			while(!stop_)
			{
				nowTime_ = new Date().getTime();
				if((nowTime_ - lastTime_) < intervalTime_)
				{
						Thread.sleep(sleepTime_);
					continue;
				}
				
				doSomething();
				
				lastTime_ = nowTime_;				
			}
			
			stopped_ = true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void start()
	{
		if(thread_ != null)
			return; ///already running
		
		thread_ = new Thread(this);
		thread_.start();					
	}

	public void stop()
	{
		stop_ = true;
		
		while(!stopped_)
			Thread.yield();
		
		thread_.interrupt();
		thread_ = null;
		
		reset();		
	}
	
	private void reset()
	{
		/// reset for next time
		stop_ = false;
		stopped_ = false;
	}
	
	private void doSomething() throws TwitterException
	{
		List<BatMessage> lsBatMsgs = tweetChecker.check();

		setChanged();
	    notifyObservers(lsBatMsgs);
	}		
}