package com.company;

public class Main {

    public static void main(String[] args) {
        ArithmeticParser parser = new ArithmeticParser("(15+9)/((2-(4*((-1))))+-1)");
        System.out.println(parser.calculate());
    }
}
