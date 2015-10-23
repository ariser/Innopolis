package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> result = new ArrayList<String>();

        try {
            Scanner in = new Scanner(new File("numbers.in"), "utf-8");

            while (in.hasNext()) {
                try {
                    long nextLong = in.nextLong();
                    result.add(nextLong % 2 == 0 ? "even" : "odd");
                } catch (InputMismatchException e) {
                    in.next();
                    result.add("nan");
                }
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }

        File out = new File("numbers.out");
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(out, false);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(String.join(" ", result));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
