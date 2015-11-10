package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        initTest();
    }

    private static void initTest() {
        File out = new File("data.in");
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
            List<String> l = new ArrayList<>();

//            l.add("1 -3 4 5 -46 68 67 23");

//            for (int i = 0; i < 60000; i++) {
//                l.add(1 + " " + 1);
//            }
//            l.add(0 + " " + 0);

//            for (int i = 0; i < 60000; i++) {
//                l.add(1 + " " + 1);
//            }
//            for (int i = 0; i < 60000; i++) {
//                l.add(5 + " " + 5);
//            }

            for (int i = 0; i < 60000; i++) {
                l.add(1 + " " + 1);
            }
            l.add(2 + " " + 2);
            l.add(3 + " " + 3);
            l.add(4 + " " + 4);
            l.add(5 + " " + 5);

            bw.write(String.join(" ", l));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
