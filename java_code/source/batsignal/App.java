package batsignal;

import java.util.Date;
import java.util.List;
import java.util.Observable;

import twitter4j.TwitterException;

public class App extends Observable implements Runnable {
	
	private final long SECOND = 1000;
//	private final long MINUTE = 60 * SECOND;
	private final long MS = 1;
	
	private final long maxIntervalTime_ = 90 * SECOND;
	private final long sleepTime_ = 100 * MS;
	
	private volatile boolean stop_ = false;
	private volatile boolean stopped_ = false;
	
	private long lastTime_ = 0;
	private long nowTime_ = 0; 
	private long intervalTime_ = 0;
	
	private Thread 			thread_ 	 = null;		
	private TweetChecker 	tweetChecker_ = null;
	
	public App(TweetChecker tweetChecker) {
		tweetChecker_ = tweetChecker;
	}

	public synchronized void start()
	{
		if(thread_ != null)
			return; ///already running
		
		thread_ = new Thread(this);
		thread_.start();					
	}

	public synchronized void stop()
	{
		stop_ = true;
		
		while(!stopped_)
			Thread.yield();
		
		thread_.interrupt();
		thread_ = null;
		
		reset();		
	}
		
	/**
	 * Don't call the run()-method directly use start() and stop()!
	 */
	@Override
	public synchronized void run() 
	{
		try {
							
			lastTime_ = 0;
			
			while(!stop_)
			{
				nowTime_ = new Date().getTime();
				
				intervalTime_ = (nowTime_ - lastTime_);
	
				if(intervalTime_ < maxIntervalTime_)
				{
					Thread.sleep(sleepTime_);
					continue;
				}
								
				lastTime_ = nowTime_;
				doTweetCheck();												
			}
			
			stopped_ = true;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}	
	}
	
	private void reset()
	{
		/// reset for next time
		stop_ = false;
		stopped_ = false;
	}
	
	private void doTweetCheck() throws TwitterException
	{
		List<BatMessage> lsBatMsgs = tweetChecker_.check();

		setChanged();
	    notifyObservers(lsBatMsgs);
	}		
}