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
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WordCounter {
	
	public static final String FILE_PATH = "C:/ABBA.txt";

	public static void main(String[] args)  {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������� ���������� ���� �� �����: ");
		System.out.flush();
		String filename = scanner.nextLine();
		Path path = Paths.get(filename);
		Stream<String> lines;
		try {
			lines = Files.lines(path, Charset.forName("windows-1251"));
			Map<String, Long> wordCount = getWordCounts(lines);
			wordCount.forEach((k, v) -> System.out.println(String.format("%s - %d", k, v)));
		} catch (FileNotFoundException e) {
			System.out.println("���� �� ������");
		} catch (IOException e) {
			System.out.println("������ �����-������");
		}
	}

	public static Map<String, Long> getWordCounts(Stream<String> lines) {
		return lines.flatMap(line -> Arrays.stream(line.trim().split("\\s")))
				.map(word -> word.replaceAll("[^�-��-�]", "").toLowerCase().trim()).filter(word -> word.length() > 0)
				.map(word -> new SimpleEntry<>(word, 1))
				.collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
	}
}