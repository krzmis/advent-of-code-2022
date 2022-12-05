package main.dev.misiek.day5;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;

public class Day5 {

    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day5/test.txt");


    public static void main(String[] args) {
        List<String> input = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();

        int numberOfContainers = 0;
        Map<Integer, Deque<String>> stackOfCreates = new HashMap<>();
        Deque<String> stack = new ArrayDeque<>();

        for (String line : input) {

            line.chars().forEachOrdered(sign -> {
                if (Character.isLetter(sign))
            });




            if (doLineContainDigits(line)) numberOfContainers = getNumberOfContainers(line);

            if (line.startsWith("move")) break;


        }

//        System.out.printf("Part 1: %d\n", getMaxCals(maxEntry, 1));
//        System.out.printf("Part 2: %d\n", getMaxCals(maxEntry, 3));
    }

    private static boolean doLineContainDigits(String line) {
        return Character.isDigit(line.charAt(line.length() - 1));
    }

    private static int getNumberOfContainers(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(line.length() - 1)));
    }

}
