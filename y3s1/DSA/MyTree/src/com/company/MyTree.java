package com.company;

import java.util.function.Function;

public interface MyTree<E> {
    Node getRoot();

    Node setRoot(E value);

    void traversalPreorder(Function<Node, Object> callback);

    void traversalPostorder(Function<Node, Object> callback);

    interface Node<T> {
        void setValue(T value);

    }
}
