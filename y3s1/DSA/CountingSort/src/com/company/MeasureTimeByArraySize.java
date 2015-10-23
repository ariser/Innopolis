package com.company;

import java.util.Random;

public class MeasureTimeByArraySize {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arrayToSort = ArrayGenerator.generate(0, 1000, random.nextInt(100000000));

            long startTime = System.currentTimeMillis();
            CountingSort.sort(arrayToSort);
            long endTime = System.currentTimeMillis();

            long elapsedTime = endTime - startTime;

            logTime(arrayToSort.length, elapsedTime);
        }
    }

    private static void logTime(int size, long time) {
        Log.addEntry("logs/time_by_size.csv", String.valueOf(size) + "," + String.valueOf(time));
    }

}