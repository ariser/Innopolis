import sort.Sort;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("A");
        test.add("Q");
        test.add("G");
        test.add("C");
        test.add("Y");
        test.add("H");
        test.add("P");
        Sort.mergeSort(test);
        System.out.println(test);
    }
}
