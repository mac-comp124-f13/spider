package edu.macalester.comp124.concurrentspider;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SharedSpiderData {
	private static final int QUEUE_SIZE = 8000;  // ARBITRARY NUMBER FOR NOW- 
	//if it's too small, things stop when it fills. However, if it is too large, 
	//the corresponding structure holding the links encountered gets very large.

	/**
	 * Urls waiting to be scraped.  The "work" left to do.
	 */
	private BlockingQueue<String> work = new ArrayBlockingQueue<String>(QUEUE_SIZE);
	/**
	 * URLs that have already been retrieved.
	 */
	private Queue<String> finished = new ConcurrentLinkedQueue<String>();
	/**
	 * Contains the count of each URL that was encountered
	 */
	private ConcurrentAllWordsCounter urlCounter = new ConcurrentAllWordsCounter();
	
	/**
	 * Getter for part of the shared data.
	 * @return the java.util.concurrent.ArrayBlockingQueue used for holding the URLS to work on
	 */
	public BlockingQueue<String> getWork() {
		return work;
	}
	/**
	 * Getter for part of the shared data.
	 * @return the java.util.concurrent.ConcurrentLinkedQueue that holds those links that have been scraped.
	 */
	public Queue<String> getFinished() {
		return finished;
	}
	/**
	 * Getter for the word counting helper class, which contains shared data.
	 * @return the ConcurrentAllWordsCounter object where we keep counts of URLs
	 */
	public ConcurrentAllWordsCounter getUrlCounter() {
		return urlCounter;
	}


}
