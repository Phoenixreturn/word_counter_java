package ru.phoenix.returned.counter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordCounterTest {
	static Stream<String> lines;
	static Map<String, Long> wordCount;

	@BeforeClass
	public static void initTestData() {
		String[] testData = { "привет –оберт привет как дела привет роберт как дела это ћатрица",
				"привет –оберт привет как дела привет роберт как дела музыка",
				"привет –оберт привет как дела привет роберт как дела истина",
				"привет –оберт привет как дела привет роберт как дела спокойствие",
				"привет –оберт привет как дела привет роберт как дела дзен" };

		lines = Arrays.stream(testData);
		wordCount = WordCounter.getWordCounts(lines);
	}

	@Test
	public void testCount() {
		assertEquals(10, wordCount.size());
	}
	
	@Test
	public void testFirstWord() {
		assertTrue(wordCount.containsKey("привет"));
		assertEquals(Long.valueOf(15), wordCount.get("привет"));
	}	
	
	@Test
	public void testSecondWord() {
		assertTrue(wordCount.containsKey("роберт"));
		assertEquals(Long.valueOf(10), wordCount.get("роберт"));
	}	
	
	@Test
	public void testThirdWord() {
		assertTrue(wordCount.containsKey("дзен"));
		assertEquals(Long.valueOf(1), wordCount.get("дзен"));
	}	
}
