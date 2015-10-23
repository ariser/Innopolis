package bsearch;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int dataLength = 1000;
        int[] data = new int[dataLength];
        for (int i = 0; i < dataLength; i++) {
            data[i] = i * 2;
        }
        Random random = new Random();
        int searchFor = random.nextInt(dataLength);
        System.out.println("Looking for: " + searchFor);
        int index = BSearchLoop.indexOf(data, searchFor);
        System.out.println("Found index: " + index + ", " + data[index]);
    }
}
