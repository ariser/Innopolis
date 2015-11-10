package heap;

public class MyHeap<K extends Comparable<K>, V> {
    protected MyArrayTree<Node<K, V>> binTree;

    MyHeap() {
        binTree = new MyArrayTree<>(2);
    }

    MyHeap(K rootKey, V rootValue) {
        binTree = new MyArrayTree<>(2, new Node<>(rootKey, rootValue));
    }

    public void insert(K key, V value) {
        
    }

    protected class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
          return key;
        }

        V getValue() {
            return value;
        }

        void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return this.key.compareTo(o.key);
        }
    }
}
