package com.company;

import java.util.Random;

public class MeasureMemoryByMaxElem {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // Get the Java runtime
            Runtime runtime = Runtime.getRuntime();
            // Run the garbage collector
            runtime.gc();

            int[] arrayToSort = ArrayGenerator.generate(0, random.nextInt(10000000), 10000000);

            CountingSort.sort(arrayToSort);

            // Calculate the used memory
            long memory = runtime.totalMemory() - runtime.freeMemory();

            logMemory(arrayToSort[arrayToSort.length - 1], memory);
        }
    }

    private static void logMemory(int maxElem, long memory) {
        Log.addEntry("logs/memory_by_max_elem.csv", String.valueOf(maxElem) + "," + String.valueOf(memory));
    }

}