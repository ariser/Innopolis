package com.company;

import java.util.Random;

public class ArrayGenerator {
    private static Random random = new Random();

    public static int[] generate(int min, int max, int length) {
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = min + random.nextInt(max - min + 1);
        }

        return array;
    }

    public static int[] generate(int min, int max) {
        return generate(min, max, random.nextInt(Integer.MAX_VALUE));
    }

    public static int[] generate() {
        int min = random.nextInt(Integer.MAX_VALUE);
        int max;
        do {
            max = random.nextInt(Integer.MAX_VALUE);
        } while (max < min);

        return generate(min, max);
    }
}
