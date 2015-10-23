package main;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {
    private Node<E> head = null;
    private Node<E> tail = null;

    private int size = 0;

    MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return head != null ? head.getElement() : null;
    }

    public E last() {
        return tail != null ? tail.getElement() : null;
    }

    public void insert(E element, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bound: " + index);
        }

        if (index == 0) {
            head = new Node<E>(element, head);
            if (tail == null) {
                tail = head;
            }
        } else {
            Node<E> currentNode = head;
            for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
                currentNode = currentNode.getNext();
            }

            Node<E> newNode = new Node<E>(element, currentNode.getNext());
            currentNode.setNext(newNode);

            if (index == size) {
                tail = newNode;
            }
        }

        size++;
    }

    public void prepend(E element) {
        insert(element, 0);
    }

    public void append(E element) {
        insert(element, size);
    }

    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bound: " + index);
        }

        E element = null;

        if (index == 0) {
            element = head.getElement();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        } else {
            Node<E> currentNode = head;
            for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
                currentNode = currentNode.getNext();
            }

            Node<E> targetNode = currentNode.getNext();
            element = targetNode.getElement();
            currentNode.setNext(targetNode.getNext());

            if (index == size - 1) {
                tail = currentNode;
            }
        }

        size--;

        return element;
    }

    public E deleteFirst() {
        if (isEmpty()) {
            return null;
        }
        return delete(0);
    }

    public E deleteLast() {
        if (isEmpty()) {
            return null;
        }
        return delete(size - 1);
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bound: " + index);
        }

        Node<E> currentNode = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getElement();
    }

    public void update(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bound: " + index);
        }

        Node<E> currentNode = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
            currentNode = currentNode.getNext();
        }

        currentNode.setElement(element);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private Node<E> lastNode;
        private Node<E> nextNode;

        MyIterator() {
            lastNode = null;
            nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            lastNode = nextNode;
            nextNode = nextNode.getNext();
            return lastNode.getElement();
        }
    }

    private class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
