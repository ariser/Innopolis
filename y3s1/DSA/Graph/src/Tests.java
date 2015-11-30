import graph.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Tests {
    public static void main(String[] args) {
        try {
            File file = new File("graph.in");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String str = new String(data, "UTF-8");

            Graph<String, String> g = Graph.fromString(str, String.class, String.class);

            System.out.println(g.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
