package com.company;

// o(n) is omega(n), not oh-little(n).

public class CountingSort {

    public static void sort(int[] source) { // 1
        if (source == null || source.length == 0) { // 1
            return; // 1
        }

        int min = Integer.MAX_VALUE; // 1
        int max = Integer.MIN_VALUE; // 1

        for (int i = 0; i < source.length; i++) { // n + 1
            if (source[i] > max) { // n
                max = source[i]; // O(n) o(1)
            }
            if (source[i] < min) { // n
                min = source[i]; // O(n) o(1)
            }
        }

        // O(5n + 6) o(3n + 8)

        sort(source, min, max); // O(n + k)
    } // O(6n + k + 6) o(4n + k + 8) // so, O(n + k) o(n + k)

    public static void sort(int[] source, int min, int max) { // 1
        if (source == null || source.length == 0) { // 1
            return; // 1
        }

        if (min > max) { // 1
            throw new IllegalArgumentException("min can't be bigger that max"); // 1
        }

        int[] counter = new int[max - min + 1]; // 1

        // k is (max-min)
        for (int i = min; i <= max; i++) { // k + 2
            counter[i - min] = 0; // k + 1
        }

        for (int i = 0; i < source.length; i++) { // n + 1
            counter[source[i] - min] += 1; // n
        }

        int writeIndex = 0; // 1
        for (int i = min; i <= max; i++) { // k + 2
            for (int j = 0; j < counter[i - min]; j++) { // n
                source[writeIndex++] = i; // n
            }
        } // 4n + 3k + 13 // so, O(n + k)
    }

}