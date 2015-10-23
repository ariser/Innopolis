package com.company;

import java.util.Random;

public class MeasureTimeByMaxElem {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arrayToSort = ArrayGenerator.generate(0, random.nextInt(1000000), 1000000);

            long startTime = System.currentTimeMillis();
            CountingSort.sort(arrayToSort);
            long endTime = System.currentTimeMillis();

            long elapsedTime = endTime - startTime;

            logTime(arrayToSort[arrayToSort.length - 1], elapsedTime);
        }
    }

    private static void logTime(int maxElem, long time) {
        Log.addEntry("logs/time_by_max_elem.csv", String.valueOf(maxElem) + "," + String.valueOf(time));
    }

}