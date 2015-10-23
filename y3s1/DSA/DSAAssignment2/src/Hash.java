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

public class Hash {

    /**
     * @param string any string
     * @return hash for the string
     */
    public static int hash(String string) {
        /*
        * this is a polynomial accumulation of a string by chars. According to the lecture, this gives us
        * little amount of collisions. Power of char index gives us a uniform distribution.
        * */

        int hash = 0;

        int string_length = string.length();
        for (int index = 0; index < string_length; index++) {
            char c = string.charAt(index);
            hash += Math.pow(33, index) * (int) c;
        }

        return hash % 1048576; // 2 ^ 20. Why not.
    }

    /**
     * TODO Your own hash function with uniform distribution for floats
     *
     * @param flt floating point number
     * @return hash code
     */
    public static int hash(Float flt) {
        // TODO: implement
        return 0;
    }

    /**
     * @param intg integer number
     * @return hash code
     */
    public static int hash(Integer intg) {
        int base = 1048576; // 2 ^ 20. Why not.
        // we multiply the given int by its module of the base, shift it in order to get rid of a sign bit.
        return intg * (intg % base) >>> 1 % base;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] operationsLine;
        String[] outputLine;

        try {
            Scanner in = new Scanner(new File("hash.in"), "utf-8");

            operationsLine = in.nextLine().trim().split("\\s+");
            outputLine = in.nextLine().trim().split("\\s+");

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        Map<String, String> map = new MyMap<>();

        String key = null;
        String valueStr = null;

        for (int i = 0; i < operationsLine.length; i++) {
            if (i % 2 == 0) {
                key = operationsLine[i];
            } else {
                valueStr = operationsLine[i];
                if (key != null) {
                    if (valueStr.equals("-")) {
                        map.remove(key);
                    } else {
                        map.put(key, valueStr);
                    }
                    key = null;
                }
            }
        }

        for (int i = 0; i < outputLine.length; i++) {
            outputLine[i] = map.get(outputLine[i]);
        }

        writeResult(String.join(" ", outputLine));
    }

    private static void writeResult(String result) {
        File out = new File("hash.out");
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

    /**
     * @param <K> key type
     * @param <V> value type
     */
    public static class MyMap<K, V> implements Map<K, V> {
        private List<Node<K, V>> table;

        MyMap() {
            this.table = new ArrayList<>();
        }

        MyMap(int initialCapacity) {
            this.table = new ArrayList<>(initialCapacity);
        }

        @Override
        public void clear() {
            for (int i = table.size(); i >= 0; i--) {
                remove(table.get(i).getKey());
            }
        }

        @Override
        public boolean containsKey(Object arg0) {
            int hash = hash(String.valueOf(arg0));
            return getNode(hash, (K) arg0) != null;
        }

        @Override
        public boolean containsValue(Object arg0) {
            for (Node<K, V> node : table) {
                if (node.getValue().equals(arg0)) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public Set<java.util.Map.Entry<K, V>> entrySet() {
            return null;
        }

        @Override
        public V get(Object arg0) {
            int hash = hash(String.valueOf(arg0));

            if (table.size() <= hash) {
                return null;
            }

            Node<K, V> resultNode = getNode(hash, (K) arg0);

            return resultNode == null ? null : resultNode.getValue();
        }

        @Override
        public boolean isEmpty() {
            return table.isEmpty();
        }

        @Override
        public Set<K> keySet() {
            Set<K> set = new HashSet<>();
            for (Node<K, V> node : table) {
                set.add(node.getKey());
            }
            return set;
        }

        @Override
        public V put(K arg0, V arg1) {
            int hash = hash(String.valueOf(arg0));

            // extend our table to the new index (which is hash)
            if (hash >= table.size()) {
                for (int i = table.size(); i <= hash; i++) {
                    table.add(null);
                }
            }

            Node<K, V> currentNode = getNode(hash, arg0);

            V existingVal = currentNode == null ? null : currentNode.getValue();

            // the hash could've been increased in the getNode func. Sorry for the code duplication.
            if (hash >= table.size()) {
                for (int i = table.size(); i <= hash; i++) {
                    table.add(null);
                }
            }

            table.set(hash, new Node<>(arg0, arg1));

            return existingVal;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> arg0) {

        }

        @Override
        public V remove(Object arg0) {
            int hash = hash(String.valueOf(arg0));

            Node<K, V> targetNode = getNode(hash, (K) arg0);

            return targetNode.setValue(null);
        }

        private Node<K, V> getNode(int hash, K key) {
            Node<K, V> currentNode = table.get(hash);

            // until we find a node which is either not exist or contains the given key, keep iterating thru the table with step = 13
            while (currentNode != null && !currentNode.getKey().equals(key)) {
                hash = hash + 13;
                currentNode = hash <= table.size() - 1 ? table.get(hash) : null;
            }

            return currentNode;
        }

        @Override
        public int size() {
            return table.size();
        }

        @Override
        public Collection<V> values() {
            Collection<V> values = new ArrayList<>();
            for (Node<K, V> node : table) {
                values.add(node.getValue());
            }
            return values;
        }

        public static class Node<K, V> implements Map.Entry<K, V> {
            private K key = null;
            private V value = null;

            Node(K key, V val) {
                this.key = key;
                this.value = val;
            }

            @Override
            public K getKey() {
                return key;
            }

            @Override
            public V getValue() {
                return value;
            }

            @Override
            public V setValue(V value) {
                V oldValue = this.value;
                this.value = value;
                return oldValue;
            }
        }
    }
}
