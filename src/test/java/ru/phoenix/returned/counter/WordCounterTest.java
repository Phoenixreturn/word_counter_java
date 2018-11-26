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
		String[] testData = { "������ ������ ������ ��� ���� ������ ������ ��� ���� ��� �������",
				"������ ������ ������ ��� ���� ������ ������ ��� ���� ������",
				"������ ������ ������ ��� ���� ������ ������ ��� ���� ������",
				"������ ������ ������ ��� ���� ������ ������ ��� ���� �����������",
				"������ ������ ������ ��� ���� ������ ������ ��� ���� ����" };

		lines = Arrays.stream(testData);
		wordCount = WordCounter.getWordCounts(lines);
	}

	@Test
	public void testCount() {
		assertEquals(10, wordCount.size());
	}
	
	@Test
	public void testFirstWord() {
		assertTrue(wordCount.containsKey("������"));
		assertEquals(Long.valueOf(15), wordCount.get("������"));
	}	
	
	@Test
	public void testSecondWord() {
		assertTrue(wordCount.containsKey("������"));
		assertEquals(Long.valueOf(10), wordCount.get("������"));
	}	
	
	@Test
	public void testThirdWord() {
		assertTrue(wordCount.containsKey("����"));
		assertEquals(Long.valueOf(1), wordCount.get("����"));
	}	
}
