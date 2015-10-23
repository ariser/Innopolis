package main;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> alist = new MyLinkedList<Integer>();

        System.out.println(alist.size());
        System.out.println(alist.last());

        alist.insert(42, 0);

        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.last());

        alist.insert(43, 1);
        alist.insert(984, 2);
        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.get(1));
        System.out.println(alist.last());

        alist.update(0, 54);
        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.get(1));
        System.out.println(alist.last());

        int r = alist.delete(1);
        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.last());

        alist.append(9);
        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.get(1));
        System.out.println(alist.get(2));
        System.out.println(alist.last());

        alist.prepend(81);
        System.out.println(alist.size());
        System.out.println(alist.first());
        System.out.println(alist.get(0));
        System.out.println(alist.get(1));
        System.out.println(alist.get(2));
        System.out.println(alist.get(3));
        System.out.println(alist.last());

        System.out.println("!");

        System.out.println(alist.size());
        for (int item : alist) {
            System.out.println(item);
        }
    }
}
