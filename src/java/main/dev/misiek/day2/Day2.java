package main.dev.misiek.day2;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {
    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day2/input.txt");
    public static final int A = 1; // Rock
    public static final int B = 2; // Paper
    public static final int C = 3; // Scissors
    public static final int X = 1; // Rock
    public static final int Y = 2; // Paper
    public static final int Z = 3; // Scissors

    public static final int LOSE = 0;
    public static final int DRAW = 3;
    public static final int WIN = 6;


    public static void main(String[] args) {
        List<String> strategy = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();

        AtomicInteger player2 = new AtomicInteger();

        strategy.forEach(rawScore -> {
            final String[] splitScore = rawScore.split(" ");

//            part1(splitScore);
            part2(splitScore);

            player2.addAndGet(resolveScore(splitScore[1]));
            System.out.println(Arrays.toString(splitScore));

            player2.addAndGet(resolveOutcome(splitScore));
            System.out.printf("Player 2 score = %d\n", player2.get());
        });
    }

    private static void part1(String[] splitScore) {
        if (splitScore[0].equals("A")) splitScore[1] = "Y";
        if (splitScore[0].equals("B")) splitScore[1] = "X";
        if (splitScore[0].equals("C")) splitScore[1] = "Z";
    }

    private static void part2(String[] splitScore) {
        if (splitScore[1].equals("X")) {
            // LOSE
            if (splitScore[0].equals("A")) splitScore[1] = "Z";
            if (splitScore[0].equals("B")) splitScore[1] = "X";
            if (splitScore[0].equals("C")) splitScore[1] = "Y";
        } else if (splitScore[1].equals("Y")) {
            // DRAW
            if (splitScore[0].equals("A")) splitScore[1] = "X";
            if (splitScore[0].equals("B")) splitScore[1] = "Y";
            if (splitScore[0].equals("C")) splitScore[1] = "Z";
        } else {
            // WIN
            if (splitScore[0].equals("A")) splitScore[1] = "Y";
            if (splitScore[0].equals("B")) splitScore[1] = "Z";
            if (splitScore[0].equals("C")) splitScore[1] = "X";
        }
    }

    private static int resolveScore(String score) {
        return switch (score) {
            case "A" -> A;
            case "B" -> B;
            case "C" -> C;
            case "Y" -> Y;
            case "X" -> X;
            case "Z" -> Z;
            default -> -Integer.MAX_VALUE;
        };
    }

    private static int resolveOutcome(String[] rawScore) {
        if (rawScore[0].equals("A") && rawScore[1].equals("X")) return DRAW;
        if (rawScore[0].equals("A") && rawScore[1].equals("Y")) return WIN;
        if (rawScore[0].equals("A") && rawScore[1].equals("Z")) return LOSE;

        if (rawScore[0].equals("B") && rawScore[1].equals("X")) return LOSE;
        if (rawScore[0].equals("B") && rawScore[1].equals("Y")) return DRAW;
        if (rawScore[0].equals("B") && rawScore[1].equals("Z")) return WIN;

        if (rawScore[0].equals("C") && rawScore[1].equals("X")) return WIN;
        if (rawScore[0].equals("C") && rawScore[1].equals("Y")) return LOSE;
        if (rawScore[0].equals("C") && rawScore[1].equals("Z")) return DRAW;

        throw new RuntimeException();
    }
}