package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Radius: ");
        String RStr = scanner.nextLine();
        Integer R = null;
        do {
            try {
                R = Integer.parseInt(RStr);
            } catch (Exception e) {
            }
        } while (R == null);

        System.out.print("Vertices amount: ");
        String verticesAmountStr = scanner.nextLine();
        Integer verticesAmount = null;
        do {
            try {
                verticesAmount = Integer.parseInt(verticesAmountStr);
            } catch (Exception e) {
            }
        } while (verticesAmount == null);

        CircleApproximation approx = new CircleApproximation(R, verticesAmount);

        System.out.println("Paste these values into your chart editor:");
        System.out.print(approx);

        scanner.close();
    }
}
