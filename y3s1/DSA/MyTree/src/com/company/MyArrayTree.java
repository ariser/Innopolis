package com.company;

import java.lang.reflect.Array;
import java.util.function.Function;

public class MyArrayTree<E> {

    private Node[] elements;

    private int k;

    MyArrayTree(int k) {
        this.k = k;
        this.elements = (Node[]) Array.newInstance(Node.class, 1);
    }

    MyArrayTree(int k, E rootValue) {
        this.k = k;
        this.elements = (Node[]) Array.newInstance(Node.class, 1);
        setRoot(rootValue);
    }

    private void allocateSpace() {
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


    public void traversalPreorder(Function<Node, Object> callback) {
        performTraversalPreorder(getRoot(), callback);
    }

    private void performTraversalPreorder(Node node, Function<Node, Object> callback) {
        if (node != null) {
            callback.apply(node);
            Node child;
            for (int i = 0; i < k; i++) {
                child = node.getChild(i);
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
                child = node.getChild(i);
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

        private int getParentIndexInArray() {
            return (index - 1) / k;
        }

        private int getChildIndexInArray(int childIndex) {
            return index * k + childIndex + 1;
        }

        E getValue() {
            return value;
        }

        void setValue(E value) {
            this.value = value;
        }

        Node getParent() throws ArrayIndexOutOfBoundsException {
            return elements[getParentIndexInArray()];
        }

        Node getChild(int childIndex) {
            int childIndexInArray = getChildIndexInArray(childIndex);
            return elements.length - 1 >= childIndexInArray ? elements[childIndexInArray] : null;
        }

        Node addChild(int childIndex, E value) {
            Node prevNode = getChild(childIndex);
            int childIndexInArray = getChildIndexInArray(childIndex);
            while (childIndexInArray >= elements.length) {
                allocateSpace();
            }
            elements[childIndexInArray] = new Node(value, childIndexInArray);
            return prevNode;
        }

        void remove() {
            // remove the node itself and all its children
            performTraversalPostorder(this, new Function<Node, Object>() {
                @Override
                public Object apply(Node node) {
                    elements[node.index] = null;
                    return null;
                }
            });
        }

        void addSubtree(int childIndex, MyArrayTree<E> tree) {
            Node prevNode = getChild(childIndex);
            if (prevNode != null) {
                prevNode.remove();
            }
            addSubtreeRecursive(this, tree.getRoot(), childIndex);
        }

        void addSubtreeRecursive(Node parent, Node node, int childIndex) {
            if (parent == null || node == null) {
                return;
            }
            parent.addChild(childIndex, node.getValue());
            Node newChild = parent.getChild(childIndex);
            for (int i = 0; i < k; i++) {
                addSubtreeRecursive(newChild, node.getChild(i), i);
            }
        }

    }
}
