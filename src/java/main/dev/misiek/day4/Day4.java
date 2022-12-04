package main.dev.misiek.day4;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 {
    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day4/input.txt");

    public static void main(String[] args) {
        List<String> content = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();

        AtomicInteger fullyContainedPairs = new AtomicInteger();
        content.forEach(line -> {
            String[] stringPairs = line.split(",");

            if (isFirstPairContainedInSecondPair(stringPairs) || isSecondPairContainedInFirstPair(stringPairs)) {
                fullyContainedPairs.getAndIncrement();
            }
        });
        System.out.printf("Part 1: %d\n", fullyContainedPairs.get());


        AtomicInteger overlappedPairs = new AtomicInteger();
        content.forEach(line -> {
            String[] stringPairs = line.split(",");

            if (isFirstPairPartOfSecondPair(stringPairs) || isSecondPairPartOfFirstPair(stringPairs)) {
                overlappedPairs.getAndIncrement();
            }
        });

        System.out.printf("Part 2: %d\n", overlappedPairs.get());
    }

    private static boolean isFirstPairContainedInSecondPair(String[] stringPairs) {
        return firstNumberSecondPair(stringPairs) <= firstNumberFirstPair(stringPairs)
                && secondNumberSecondPair(stringPairs) >= secondNumberFirstPair(stringPairs);
    }

    private static boolean isSecondPairContainedInFirstPair(String[] stringPairs) {
        return firstNumberFirstPair(stringPairs) <= firstNumberSecondPair(stringPairs)
                && secondNumberFirstPair(stringPairs) >= secondNumberSecondPair(stringPairs);
    }

    private static boolean isFirstPairPartOfSecondPair(String[] stringPairs) {
        return (firstNumberFirstPair(stringPairs) <= firstNumberSecondPair(stringPairs)
                && secondNumberFirstPair(stringPairs) >= secondNumberSecondPair(stringPairs))
                    || (firstNumberFirstPair(stringPairs) <= secondNumberSecondPair(stringPairs)
                    && secondNumberFirstPair(stringPairs) >= secondNumberSecondPair(stringPairs));
    }

    private static boolean isSecondPairPartOfFirstPair(String[] stringPairs) {
        return (firstNumberSecondPair(stringPairs) <= firstNumberFirstPair(stringPairs)
                && secondNumberSecondPair(stringPairs) >= secondNumberFirstPair(stringPairs))
                    || (firstNumberSecondPair(stringPairs) <= secondNumberFirstPair(stringPairs)
                    && secondNumberSecondPair(stringPairs) >= secondNumberFirstPair(stringPairs));
    }

    private static int firstNumberFirstPair(String[] stringPairs) {
        return Integer.parseInt(stringPairs[0].split("-")[0]);
    }

    private static int secondNumberFirstPair(String[] stringPairs) {
        return Integer.parseInt(stringPairs[0].split("-")[1]);
    }

    private static int firstNumberSecondPair(String[] stringPairs) {
        return Integer.parseInt(stringPairs[1].split("-")[0]);
    }

    private static int secondNumberSecondPair(String[] stringPairs) {
        return Integer.parseInt(stringPairs[1].split("-")[1]);
    }

}