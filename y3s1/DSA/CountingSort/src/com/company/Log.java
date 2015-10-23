package com.company;

import java.io.*;
import java.nio.charset.Charset;

public class Log {
    public static void addEntry(String path, String entry) {
        File out = new File(path);
        if (!out.exists()) {
            out.getParentFile().mkdirs();
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(out, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(entry);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
