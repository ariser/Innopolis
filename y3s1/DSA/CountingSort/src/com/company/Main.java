package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arrayToSort = ArrayGenerator.generate();

        System.out.println(Arrays.toString(arrayToSort));
        CountingSort.sort(arrayToSort);
        System.out.println(Arrays.toString(arrayToSort));
    }

}