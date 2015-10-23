/**
 * @author Nikolay Troshkov
 * @email n.troshkov@innopolis.ru
 * @date 29 Sep, 2015
 * In accordance with the academic honor, I (Nikolay Troshkov) certify that
 * the answers here are my own work without copying of others and
 * solutions from Internet or other sources.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Main {
    /**
     * @param data
     *            - list of data to search. Assume data is sorted
     * @param value
     *            - value we are searching for
     * @return index of the element in array if present, else -1
     */
    @SuppressWarnings({ "rawtypes" })
    public static <T> int binarySearch(List<T> data, Comparable value) {
        int low = 0;
        int mid = -1;
        int high = data.size() - 1;

        Comparable midValue;

        while (low <= high) {
            mid = (low + high) / 2;
            midValue = (Comparable)data.get(mid);

            if (value.equals(midValue)) {
                return mid;
            }

            if (value.compareTo(midValue) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * @param data
     *            data to sort
     */
    @SuppressWarnings("rawtypes")
    public static <T> void sort(List<T> data) {
        // insertion sort
        for (int i = 0; i < data.size(); i++) {
            T currentVal = data.get(i);
            Comparable currentValComparable = (Comparable)currentVal;
            int j = i;
            while (j > 0 && currentValComparable.compareTo((Comparable)data.get(j-1)) < 0) {
                data.set(j, data.get(j - 1));
                j--;
            }
            data.set(j, currentVal);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String dataType;
        String dataLine;
        String[] data;
        List<Float> dataFloat;
        List<Integer> dataInteger;
        List<String> dataString;
        String valueToFind;

        try {
            Scanner in = new Scanner(new File("sort.in"), "utf-8");

            dataType = in.next();
            dataLine = in.nextLine().trim();
            valueToFind = in.next();

            data = dataLine.split(" ", -1);

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        int index = -1;

        switch (dataType) {
            case "Float":
                dataFloat = new ArrayList<>(data.length);
                for (String item : data) {
                    dataFloat.add(Float.parseFloat(item));
                }
                sort(dataFloat);
                index = binarySearch(dataFloat, (Comparable) Float.parseFloat(valueToFind));
                break;
            case "Integer":
                dataInteger = new ArrayList<>(data.length);
                for (String item : data) {
                    dataInteger.add(Integer.parseInt(item));
                }
                sort(dataInteger);
                index = binarySearch(dataInteger, (Comparable) Integer.parseInt(valueToFind));
                break;
            case "String":
                dataString = new ArrayList<>(data.length);
                for (String item : data) {
                    dataString.add(item);
                }
                sort(dataString);
                index = binarySearch(dataString, (Comparable) valueToFind);
                break;
        }

        writeResult(String.valueOf(index));
    }

    private static void writeResult(String result) {
        File out = new File("sort.out");
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
            bw.write(result);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
