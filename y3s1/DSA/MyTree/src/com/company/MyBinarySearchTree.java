package com.company;

public class MyBinarySearchTree<E extends Comparable<E>> {
    protected MyBinaryTree<E> binTree;

    MyBinarySearchTree() {
        binTree = new MyBinaryTree<>();
    }

    MyBinarySearchTree(E rootValue) {
        binTree = new MyBinaryTree<>(rootValue);
    }

    public MyBinaryTree<E>.Node<E> find(E value) {
        return doSearch(binTree.getRoot(), value);
    }

    protected MyBinaryTree<E>.Node<E> doSearch(MyBinaryTree<E>.Node<E> node, E value) {
        if (node == null) {
            return null;
        }

        E nodeValue = node.getValue();

        if (value.equals(nodeValue)) {
            return node;
        } else if (value.compareTo(nodeValue) < 0) {
            return doSearch(node.getLeftChild(), value);
        } else {
            return doSearch(node.getRightChild(), value);
        }
    }

    public void add(E value) {
        MyBinaryTree<E>.Node<E> root = binTree.getRoot();
        if (root == null) {
            binTree.addRoot(value);
        } else {
            addRecursive(value, root);
        }
    }

    protected void addRecursive(E value, MyBinaryTree<E>.Node<E> node) {
        if (node == null) {
            return;
        }

        E nodeValue = node.getValue();

        if (value.compareTo(nodeValue) < 0) {
            if (node.getLeftChild() == null) {
                binTree.addLeft(node, value);
            } else {
                addRecursive(value, node.getLeftChild());
            }
        } else {
            if (node.getRightChild() == null) {
                binTree.addRight(node, value);
            } else {
                addRecursive(value, node.getRightChild());
            }
        }
    }

    public void delete(E value) {
        MyBinaryTree<E>.Node<E> node = find(value);

        if (node == null) {
            return;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            // node has no children
            binTree.remove(node);
        } else if (node.getLeftChild() == null) {
            // node has only right child
            if (node.getParent().getLeftChild() == node) {
                // node is the left child of its parent
                node.getParent().setLeftChild(node.getRightChild());
            } else {
                // node is the right child of its parent
                node.getParent().setRightChild(node.getRightChild());
            }
        } else if (node.getRightChild() == null) {
            // node has only left child
            if (node.getParent().getLeftChild() == node) {
                // node is the left child of its parent
                node.getParent().setLeftChild(node.getLeftChild());
            } else {
                // node is the right child of its parent
                node.getParent().setRightChild(node.getLeftChild());
            }
        } else {
            // node has two children
            MyBinaryTree<E>.Node<E> predecessor = node.getLeftChild();
            while (predecessor.getRightChild() != null || predecessor.getLeftChild() != null) {
                if (predecessor.getRightChild() != null) {
                    predecessor = predecessor.getRightChild();
                } else {
                    predecessor = predecessor.getLeftChild();
                }
            }
            node.setValue(predecessor.getValue());
            binTree.remove(predecessor);
        }
    }

    public int size() {
        return binTree.getSize();
    }

    public int height() {
        return binTree.height();
    }

    public int height(MyBinaryTree<E>.Node<E> node) {
        return binTree.height(node);
    }

    public void print() {
        BTreePrinter.printNode(binTree.getRoot());
    }
}
