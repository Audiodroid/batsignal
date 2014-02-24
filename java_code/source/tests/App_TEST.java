package tests;
import batsignal.App;
import batsignal.TweetChecker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter4j.TwitterException;
import static org.mockito.Mockito.*;


public class App_TEST {
	
	private App app_ = null;
	private TweetChecker tweetCheckerMOCK_ = null;
	
	@Before
	public void setup() {
		tweetCheckerMOCK_ = mock(TweetChecker.class);
		app_ = new App(tweetCheckerMOCK_);
	}

	@After
	public void teardown() {
		app_ = null;	
		tweetCheckerMOCK_ = null;
	}
	
	@Test
	public void TestDoubleStartStopWorks() throws TwitterException, InterruptedException
	{
		app_.start();
		Thread.sleep(2);
		app_.stop();
		
		app_.start();
		Thread.sleep(2);
		app_.stop();
		
		verify (tweetCheckerMOCK_, atLeast(2)).check(); //JUnit4
				

//		when(instrKeySnd1_.isPlaying()).thenReturn(false);
//		assertFalse(instrInterval_.isPlaying());

//		InOrder inOrder1 = inOrder(instrKeySnd1_);
//		InOrder inOrder2 = inOrder(instrKeySnd2_);
//
//		inOrder1.verify(instrKeySnd1_).load((InstrKey) any(),
//										(OnCompletionListener) any());
//		inOrder2.verify(instrKeySnd2_).load((InstrKey) any(), 
//										(OnCompletionListener) eq(null));
//		
//		inOrder1.verify(instrKeySnd1_).play();
//		inOrder2.verify(instrKeySnd2_).play();				

//		verify(instrKeySnd1_).stop();

	}
};	
