package edu.macalester.comp124.concurrentspider;


/**
 * This contains main and the declarations of the data structures to be shared
 * by the 'Runnable' threads that get started.
 * 
 * @author shoop
 *
 */
public class RunThreadedSpider {
	private static final String BEGINNING_URL = "http://www.macalester.edu";
	private static final int NUM_THREADS = 8;
	
	// The pattern in these threaded shared memory programs is to declare the
	// shared data structures here in the 'main' class first, then pass them
	// into the constructor of the 'Runnable' for each new thread started.
	// In this case, we have a helper class containing the shared data.
	private static SharedSpiderData sharedData = new SharedSpiderData();

	/**
	 * Run the concurrent spider program.
	 * @param args
	 */
	public static void main(String [] args) {
//		Spider spider = new Spider(10);
//		spider.crawl(BEGINNING_URL);
		
		//launch the threads that will do the work in parallel
		Thread threads[] = new Thread[NUM_THREADS];
		for (int i=0; i < NUM_THREADS; i++ ) {
			threads[i] = startThread(i);
			println("Thread" + i + " started");
		}
		
		// wait until all threads complete
		for (int i=0; i < NUM_THREADS; i++ ) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//count up the words found
		for (WordCount urlCount : sharedData.getUrlCounter().getCounts()) {
			println("url " + urlCount.getWord() + " is " + urlCount.getCount());
		}
	}
	
	/**
	 * I provided this function because students are used to calling println().
	 * @param message
	 */
	public static void println(String message) {
		System.out.println(message);
	}

	public static Thread startThread(int threadNum) {
		Thread t;
		if (threadNum != 0) {
			t = new Thread(new ConcurrentSpider(sharedData));
		} else {
			t = new Thread(new ConcurrentSpider(sharedData, BEGINNING_URL));
		}
		t.start(); // Calls back to the run method in ConcurrentSpider class
		return t;
	}

	//
}

