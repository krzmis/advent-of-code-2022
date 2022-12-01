package main.dev.misiek.day1;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;

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
                continue;
            }

            localCalTotal += Integer.parseInt(cal);
            elfToCal.put(elfCount, localCalTotal);
        }

        List<Map.Entry<Integer, Integer>> maxEntry = new ArrayList<>(elfToCal.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList());

        System.out.printf("Part 1: %d\n", getMaxCals(maxEntry, 1));
        System.out.printf("Part 2: %d\n", getMaxCals(maxEntry, 3));
    }

    private static int getMaxCals(List<Map.Entry<Integer, Integer>> maxEntry, int limit) {
        return maxEntry.stream()
                .map(Map.Entry::getValue)
                .limit(limit)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
