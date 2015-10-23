package bsearch;

import java.util.Stack;

public class BSearchLoopStack {
    private static int indexOf(int[] data, int target, int low, int high) {
        /*Stack<int[]> stack = new Stack<int[]>();

        do {
            if (low > high) {
                return -1;
            }

            int mid = (low + high) / 2;

            if (target == data[mid]) {
                return mid;
            }

            if (target < data[mid]) {
                return indexOf(data, target, low, mid - 1);
            } else {
                return indexOf(data, target, mid + 1, high);
            }
        } while (!stack.isEmpty());*/
        return -1;
    }

    public static int indexOf(int[] data, int target) {
        return indexOf(data, target, 0, data.length);
    }
}
