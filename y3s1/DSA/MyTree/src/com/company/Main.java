package com.company;

public class Main {

    public static void main(String[] args) {
        ArithmeticParser parser = new ArithmeticParser("(15+9)/((2-(4*((-1))))+-1)");
        System.out.println(parser.calculate());


//        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>(1);
//        tree.add(5);
//        tree.add(8);
//        tree.add(9);
//        tree.add(4);
//        tree.add(6);
//        tree.print();
//        tree.delete(5);
//        tree.print();
//        tree.delete(9);
//        tree.print();
//        tree.delete(8);
//        tree.print();

        MyAVLTree<String> avl = new MyAVLTree<>();
        avl.add("Mar");
        avl.add("May");
        avl.add("Nov");
        avl.add("Aug");
        avl.add("Apr");
        avl.add("Jan");
        avl.add("Dec");
        avl.add("Jul");
        avl.add("Feb");
        avl.add("Jun");
        avl.add("Oct");
        avl.add("Sep");
        avl.print();

        MyAVLTree<Integer> avl1 = new MyAVLTree<>();
        avl1.add(10);
        avl1.add(15);
        avl1.add(5);
        avl1.add(11);
        avl1.add(16);
        avl1.add(17);
        avl1.add(12);
        avl1.add(13);
        avl1.print();
        avl1.delete(5);
        avl1.delete(17);
        avl1.print();

        MyRBTree<Integer> rb = new MyRBTree<>();
        rb.add(10);
        rb.add(5);
        rb.add(15);
        rb.add(20);
        rb.add(25);
        rb.add(16);
        rb.print();
    }
}
