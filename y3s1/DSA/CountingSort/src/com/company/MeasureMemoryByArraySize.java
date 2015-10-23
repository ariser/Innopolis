package com.company;

import java.util.Random;

public class MeasureMemoryByArraySize {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // Get the Java runtime
            Runtime runtime = Runtime.getRuntime();
            // Run the garbage collector
            runtime.gc();

            int[] arrayToSort = ArrayGenerator.generate(0, 1000, random.nextInt(100000000));

            CountingSort.sort(arrayToSort);

            // Calculate the used memory
            long memory = runtime.totalMemory() - runtime.freeMemory();

            logMemory(arrayToSort.length, memory);
        }
    }

    private static void logMemory(int size, long memory) {
        Log.addEntry("logs/memory_by_size.csv", String.valueOf(size) + "," + String.valueOf(memory));
    }

}