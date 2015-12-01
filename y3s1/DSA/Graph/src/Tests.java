import graph.Graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.function.Consumer;


public class Tests {
    public static void main(String[] args) {
//        try {
//            File file = new File("graph.in");
//            FileInputStream fis = new FileInputStream(file);
//            byte[] data = new byte[(int) file.length()];
//            fis.read(data);
//            fis.close();
//
//            String str = new String(data, "UTF-8");
//
//            Graph<String, Integer> g = Graph.fromString(str, String.class);
//
//            System.out.println(g.toString());
//        } catch (FileNotFoundException e) {
//            System.out.println("Input file not found.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Graph<String, String> g = new Graph<>();
//
//        g.addVertex("A");
//        g.addVertex("B");
//        g.addVertex("C");
//        g.addVertex("D");
//        g.addVertex("E");
//        g.addVertex("F");
//        g.addVertex("G");
//        g.addVertex("H");
//        g.addVertex("I");
//
//        g.addEdge("A", "B");
//        g.addEdge("A", "C");
//        g.addEdge("A", "D");
//        g.addEdge("A", "E");
//        g.addEdge("B", "F");
//        g.addEdge("F", "H");
//        g.addEdge("D", "G");
//        g.addEdge("G", "I");
//
//        g.BFS(stringStringVertex -> System.out.println(stringStringVertex.toString()));
//        g.DFS(stringStringVertex -> System.out.println(stringStringVertex.toString()));

        Graph<String, Integer> gForMST = new Graph<>();

        gForMST.addVertex("A");
        gForMST.addVertex("B");
        gForMST.addVertex("C");
        gForMST.addVertex("D");
        gForMST.addVertex("E");
        gForMST.addVertex("F");

        gForMST.addEdge("A", "B", 6);
        gForMST.addEdge("A", "D", 4);
        gForMST.addEdge("B", "D", 7);
        gForMST.addEdge("B", "C", 10);
        gForMST.addEdge("B", "E", 7);
        gForMST.addEdge("D", "C", 8);
        gForMST.addEdge("D", "E", 12);
        gForMST.addEdge("C", "E", 5);
        gForMST.addEdge("C", "F", 6);
        gForMST.addEdge("E", "F", 7);

        Graph<String, Integer> mst = gForMST.getMinimumSpanningTree(0, Integer.MAX_VALUE);
        System.out.print("Hello world");
    }
}
