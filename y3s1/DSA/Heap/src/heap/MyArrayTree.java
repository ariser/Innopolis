package heap;

import java.lang.reflect.Array;
import java.util.function.Function;

public class MyArrayTree<E extends Comparable<E>> {

    protected Node[] elements;

    protected int k;

    protected int size;

    MyArrayTree(int k) {
        this.k = k;
        this.elements = (Node[]) Array.newInstance(Node.class, 1);
        this.size = 0;
    }

    MyArrayTree(int k, E rootValue) {
        this.k = k;
        this.elements = (Node[]) Array.newInstance(Node.class, 1);
        setRoot(rootValue);
        this.size = 1;
    }

    protected void allocateSpace() {
        int size = elements.length;
        Node[] newElements = (Node[]) Array.newInstance(Node.class, size * 2);
        System.arraycopy(elements, 0, newElements, 0, size);
        this.elements = newElements;
    }

    Node getRoot() {
        return elements[0];
    }

    Node setRoot(E value) {
        Node root = getRoot();
        elements[0] = new Node(value, 0);
        return root;
    }

    Node getParent(Node node) throws ArrayIndexOutOfBoundsException {
        return elements[getParentIndexInArray(node)];
    }

    Node getChild(Node node, int childIndex) {
        int childIndexInArray = getChildIndexInArray(node, childIndex);
        return elements.length - 1 >= childIndexInArray ? elements[childIndexInArray] : null;
    }

    Node addChild(Node node, int childIndex, E value) {
        Node prevNode = getChild(node, childIndex);
        int childIndexInArray = getChildIndexInArray(node, childIndex);
        while (childIndexInArray >= elements.length) {
            allocateSpace();
        }
        elements[childIndexInArray] = new Node(value, childIndexInArray);
        size++;
        return prevNode;
    }

    void remove(Node node) {
        // remove the node itself and all its children
        performTraversalPostorder(node, new Function<Node, Object>() {
            @Override
            public Object apply(Node node) {
                elements[node.index] = null;
                size--;
                return null;
            }
        });
    }

    void addSubtree(Node node, int childIndex, MyArrayTree<E> tree) {
        Node prevNode = getChild(node, childIndex);
        if (prevNode != null) {
            remove(prevNode);
        }
        addSubtreeRecursive(node, tree.getRoot(), childIndex);
    }

    void addSubtreeRecursive(Node parent, Node node, int childIndex) {
        if (parent == null || node == null) {
            return;
        }
        addChild(parent, childIndex, node.getValue());
        Node newChild = getChild(parent, childIndex);
        for (int i = 0; i < k; i++) {
            addSubtreeRecursive(newChild, getChild(node, i), i);
        }
    }

    protected int getParentIndexInArray(Node node) {
        return (node.index - 1) / k;
    }

    protected int getChildIndexInArray(Node node, int childIndex) {
        return node.index * k + childIndex + 1;
    }

    public int size() {
        return size;
    }

    public void traversalPreorder(Function<Node, Object> callback) {
        performTraversalPreorder(getRoot(), callback);
    }

    private void performTraversalPreorder(Node node, Function<Node, Object> callback) {
        if (node != null) {
            callback.apply(node);
            Node child;
            for (int i = 0; i < k; i++) {
                child = getChild(node, i);
                performTraversalPreorder(child, callback);
            }
        }
    }

    public void traversalPostorder(Function<Node, Object> callback) {
        performTraversalPostorder(getRoot(), callback);
    }

    private void performTraversalPostorder(Node node, Function<Node, Object> callback) {
        if (node != null) {
            Node child;
            for (int i = 0; i < k; i++) {
                child = getChild(node, i);
                performTraversalPostorder(child, callback);
            }
            callback.apply(node);
        }
    }

    public class Node {
        private E value;
        private int index;

        Node(E value, int index) {
            this.value = value;
            this.index = index;
        }

        E getValue() {
            return value;
        }

        void setValue(E value) {
            this.value = value;
        }
    }
}
