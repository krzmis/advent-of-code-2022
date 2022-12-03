package main.dev.misiek.day3;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day3 {
    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day3/input.txt");


    public static void main(String[] args) {
        List<String> content = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();
        AtomicInteger sum = new AtomicInteger();

        content.forEach(line -> {
            final CharSequence rucksack1 = line.subSequence(0, line.length() / 2);
            final CharSequence rucksack2 = line.subSequence(line.length() / 2, line.length());

            Set<Character> characters = new HashSet<>();
            rucksack1.chars().forEachOrdered(character -> {
                for (int i = 0; i < rucksack1.length(); i++) {
                    if (rucksack2.charAt(i) == character) characters.add((char) character);
                }
            });

            if (characters.size() > 1) System.out.println("List has more than common char!");
            scoreLetter(characters, sum);
        });

        System.out.printf("Part 1: %d\n", sum.get());



        AtomicInteger sum2 = new AtomicInteger();

        for (int i = 0; i < content.size(); i = i + 3) {
            Set<Character> commonChars = new HashSet<>();
            Set<Character> tempCommonChars = new HashSet<>();

            Set<Character> group1 = content.get(i).chars()
                    .mapToObj(element -> (char) element)
                    .collect(Collectors.toSet());

            Set<Character> group2 = content.get(i + 1).chars()
                    .mapToObj(element -> (char) element)
                    .collect(Collectors.toSet());

            Set<Character> group3 = content.get(i + 2).chars()
                    .mapToObj(element -> (char) element)
                    .collect(Collectors.toSet());

            group1.forEach(letter -> {
                if (group2.contains(letter)) tempCommonChars.add(letter);
            });

            tempCommonChars.forEach(common -> {
                if (group3.contains(common)) commonChars.add(common);
            });
            scoreLetter(commonChars, sum2);
        }

        System.out.printf("Part 2: %d\n", sum2.get());
    }

    private static void scoreLetter(Set<Character> characters, AtomicInteger sum) {
        characters.forEach(letter -> {
            if (letter >= 'a' && letter <= 'z') {
                sum.getAndAdd(letter - 'a' + 1);
            } else {
                sum.getAndAdd(letter - 'A' + 27);
            }
        });
    }

}
