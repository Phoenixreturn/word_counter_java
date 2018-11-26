package ru.phoenix.returned.counter;

import java.io.FileNotFoundException;
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
	
	public static final String FILE_PATH = "C:/ABBA.txt";

	public static void main(String[] args)  {
		
		Path path = Paths.get(FILE_PATH);
		Stream<String> lines;
		try {
			lines = Files.lines(path, Charset.forName("windows-1251"));
			Map<String, Long> wordCount = getWordCounts(lines);
			wordCount.forEach((k, v) -> System.out.println(String.format("%s - %d", k, v)));
		} catch (FileNotFoundException e) {
			System.out.println("‘айл не найден");
		} catch (IOException e) {
			System.out.println("ќшибка ввода-вывода");
		}
	}

	public static Map<String, Long> getWordCounts(Stream<String> lines) {
		return lines.flatMap(line -> Arrays.stream(line.trim().split("\\s")))
				.map(word -> word.replaceAll("[^а-€ј-я]", "").toLowerCase().trim()).filter(word -> word.length() > 0)
				.map(word -> new SimpleEntry<>(word, 1))
				.collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
	}
}