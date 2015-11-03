package com.company;

import java.util.HashMap;
import java.util.Map;

public class MyRBTree<E extends Comparable<E>> extends MyBinarySearchTree<E> {
    // very questionable solution, as long as we have to search the same key in the colors map as well,
    // which violates the RBTree purpose itself
    // but right now I can't extend the Node class with a color field, which would be the best solution.
    // This is a problem of initial architecture. Refactoring is needed.
    Map<E, Color> colors = new HashMap<>();

    @Override
    public void add(E value) {
        super.add(value);
        MyBinaryTree<E>.Node<E> newNode = find(value);
        setColor(newNode, newNode == binTree.getRoot() ? Color.Black : Color.Red);
        balanceInsertion(newNode);
    }

    @Override
    public void delete(E value) {
        deleteRecursive(binTree.getRoot(), value);
    }

    private void deleteRecursive(MyBinaryTree<E>.Node<E> node, E value) {
        if (node == null) {
            return;
        }

        if (value.compareTo(node.getValue()) < 0) {
            deleteRecursive(node.getLeftChild(), value);
        } else if (value.compareTo(node.getValue()) > 0) {
            deleteRecursive(node.getRightChild(), value);
        } else {
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
                deleteRecursive(predecessor, predecessor.getValue());
            }
        }

        balanceDeletion(node);
    }

    private void leftRotate(MyBinaryTree<E>.Node<E> node) {
        MyBinaryTree<E>.Node<E> rightChild = node.getRightChild();
        MyBinaryTree<E>.Node<E> rightChildLeftChild = rightChild.getLeftChild();

        if (binTree.getRoot() == node) {
            binTree.setRoot(rightChild);
            rightChild.setParent(null);
        } else {
            if (node.getParent().getLeftChild() == node) {
                node.getParent().setLeftChild(rightChild);
            } else if (node.getParent().getRightChild() == node) {
                node.getParent().setRightChild(rightChild);
            }
        }
        rightChild.setLeftChild(node);
        node.setRightChild(rightChildLeftChild);
    }

    private void rightRotate(MyBinaryTree<E>.Node<E> node) {
        MyBinaryTree<E>.Node<E> leftChild = node.getLeftChild();
        MyBinaryTree<E>.Node<E> leftChildRightChild = leftChild.getRightChild();

        if (binTree.getRoot() == node) {
            binTree.setRoot(leftChild);
            leftChild.setParent(null);
        } else {
            if (node.getParent().getLeftChild() == node) {
                node.getParent().setLeftChild(leftChild);
            } else if (node.getParent().getRightChild() == node) {
                node.getParent().setRightChild(leftChild);
            }
        }
        leftChild.setRightChild(node);
        node.setLeftChild(leftChildRightChild);
    }

    private void balanceInsertion(MyBinaryTree<E>.Node<E> newNode) {
        if (newNode == null) {
            return;
        }

        if (getColor(newNode.getParent()) == Color.Black) {
            return;
        }

        MyBinaryTree<E>.Node<E> parent = newNode.getParent();
        MyBinaryTree<E>.Node<E> grandpa = parent.getParent();
        MyBinaryTree<E>.Node<E> uncle = grandpa.getLeftChild() == parent ? grandpa.getRightChild() : grandpa.getLeftChild();

        if (getColor(parent) == Color.Red && getColor(uncle) == Color.Red) {
            setColor(parent, Color.Black);
            setColor(uncle, Color.Black);
            setColor(grandpa, Color.Red);

            if (grandpa == binTree.getRoot()) {
                setColor(grandpa, Color.Black);
            } else {
                if (getColor(grandpa.getParent()) == Color.Red) {
                    balanceInsertion(grandpa);
                }
            }
        } else if (getColor(uncle) == Color.Black) {
            if (newNode == parent.getLeftChild()) {
                if (parent == grandpa.getRightChild()) {
                    rightRotate(parent);
                    balanceInsertion(newNode);
                } else {
                    rightRotate(grandpa);
                    setColor(grandpa, Color.Red);
                    setColor(parent, Color.Black);
                }
            } else {
                if (parent == grandpa.getLeftChild()) {
                    leftRotate(parent);
                    balanceInsertion(newNode);
                } else {
                    leftRotate(grandpa);
                    setColor(grandpa, Color.Red);
                    setColor(parent, Color.Black);
                }
            }
        }
    }

    private void balanceDeletion(MyBinaryTree<E>.Node<E> node) {
    }

    private void setColor(MyBinaryTree<E>.Node<E> node, Color color) {
        colors.put(node.getValue(), color);
    }

    private Color getColor(MyBinaryTree<E>.Node<E> node) {
        if (node == null) return Color.Black;
        return colors.get(node.getValue());
    }

    private enum Color {
        Red, Black, DoubleBlack
    }
}
