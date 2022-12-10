package main.dev.misiek.day10;

import main.dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day10 {

    public static final Path INPUT_FILE_PATH = Path.of("src/java/main/dev/misiek/day10/input.txt");
    public static final Path TEST_FILE_PATH = Path.of("src/java/main/dev/misiek/day10/test.txt");

    public static void main(String[] args) {
        List<String> input = FileUtils.readFromFile(INPUT_FILE_PATH).stream()
                .toList();

        List<Instruction> instructions = input.stream()
                .map(s -> s.split(" "))
                .map(arr -> arr.length == 1 ? new Noop() : new Addx(Integer.parseInt(arr[1])))
                .collect(Collectors.toList());

        AtomicInteger cpuCycles = new AtomicInteger(0);
        AtomicInteger register = new AtomicInteger(1);
        AtomicInteger signalStrength = new AtomicInteger(0);
        AtomicInteger checkCycle = new AtomicInteger(20);

        instructions.forEach(instruction -> {
            for (int i = instruction.getCycle(); i != 0; i--) {
                cpuCycles.incrementAndGet();

                if (cpuCycles.get() == checkCycle.get()) {
                    signalStrength.addAndGet(register.get() * checkCycle.get());
                    checkCycle.addAndGet(40);

                    System.out.printf("Cycle=%s, current register=%d, sum=%d%n",
                            cpuCycles.get(), register.get(), signalStrength.get());
                }

            }

            if (instruction instanceof Addx) register.addAndGet(((Addx) instruction).value);
        });

        System.out.printf("%nTotal Cpu cycle=%d%nCurrent Register=%d%nRegisters Sum=%d%n",
                cpuCycles.get(), register.get(), signalStrength.get());
    }

    public sealed interface Instruction permits Noop, Addx {
        int getCycle();
    }

    public record Noop(int cycles) implements Instruction {
        public Noop() {
            this(1);
        }

        @Override
        public int getCycle() {
            return this.cycles;
        }
    }

    public record Addx(int cycles, int value) implements Instruction {
        public Addx(int value) {
            this(2, value);
        }

        @Override
        public int getCycle() {
            return this.cycles;
        }
    }

}
