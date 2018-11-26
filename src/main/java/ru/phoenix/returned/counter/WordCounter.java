package ru.phoenix.returned.counter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("C:/ABBA.txt");
		Stream<String> lines = Files.lines(path, Charset.forName("windows-1251"));
	    Map<String, Long> wordCount = lines.flatMap(line -> Arrays.stream(line.trim().split("\\s"))) 
	            .map(word -> word.replaceAll("[^à-ÿÀ-ß]", "").toLowerCase().trim())
	            .filter(word -> word.length() > 0)
	            .map(word -> new SimpleEntry<>(word, 1))
	            .collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
	 
	    wordCount.forEach((k, v) -> System.out.println(String.format("%s ==>> %d", k, v)));
	}
}