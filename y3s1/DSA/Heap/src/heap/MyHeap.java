package heap;

public class MyHeap<K extends Comparable<K>, V> {
    protected MyArrayTree<Node<K, V>> binTree;

    MyHeap() {
        binTree = new MyArrayTree<>(2);
    }

    MyHeap(K rootKey, V rootValue) {
        binTree = new MyArrayTree<>(2, new Node<>(rootKey, rootValue));
    }

    public void add(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        int index = binTree.size();
        while (index >= binTree.elements.length) {
            binTree.allocateSpace();
        }
        MyArrayTree.Node<Node<K, V>> node = new MyArrayTree.Node<>(newNode, index);
        binTree.elements[index] = node;
        MyArrayTree.Node<Node<K, V>> parent = binTree.getParent(node);
        while (parent.getValue().compareTo(node.getValue()) < 0) {

        }
    }

    public V peek() {
        return null;
    }

    public int size() {
        return binTree.size();
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
