package main.dev.misiek.day1;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day1 {

    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day1/input.txt");

    public static void main(String[] args) {
        int elfCount = 1;
        int localCalTotal = 0;

        Map<Integer, Integer> elfToCal = new HashMap<>();
        List<String> cals = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();

        for (String cal : cals) {
            if (cal.isEmpty()) {

                elfCount++;
                localCalTotal = 0;
            } else {
                localCalTotal += Integer.parseInt(cal);
            }

            elfToCal.put(elfCount, localCalTotal);
        }

        List<Map.Entry<Integer, Integer>> maxEntry = new ArrayList<>(elfToCal.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .toList());

        Collections.reverse(maxEntry);

        System.out.printf("Part 1: %d\n", maxEntry.stream()
                .map(Map.Entry::getValue)
                .limit(1)
                .mapToInt(Integer::intValue)
                .sum());

        System.out.printf("Part 2: %d\n", maxEntry.stream()
                .map(Map.Entry::getValue)
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum());
    }
}
