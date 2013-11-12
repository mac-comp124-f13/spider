package edu.macalester.comp124.spider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class AllWordsCounter {
	Map<String, Integer> wordMap;
	
	public AllWordsCounter() {
		wordMap = new HashMap<String, Integer>(); 
	}
	
	/**
	 * Increments the count a particular word.
	 * @param word The word to be counted.
	 */
	public void countWord(String word) {
		Integer currentCount = wordMap.get(word);
		if (currentCount == null) {
			  // Java 1.5 'unboxing' and 'boxing' of an Integer
			currentCount = 0;
		}
        currentCount++;
        wordMap.put(word, currentCount);
	}
	
	/** 
	 * @return An array of all WordCounter objects.  The array should
	 * have length equal to the number of WordCounter objects.
	 */
	public WordCount[] getCounts() {
		WordCount trimmed[] = new WordCount[wordMap.size()];
		// work here to get each key, val into a WordCount object in this array
		int idx = 0;
		for (String key : wordMap.keySet()) {
			int nextVal = wordMap.get(key);
			WordCount nextWordCount = new WordCount(key, nextVal);
			trimmed[idx] = nextWordCount;
			idx++;
		}
		Arrays.sort(trimmed);
		return trimmed;
	}

}
