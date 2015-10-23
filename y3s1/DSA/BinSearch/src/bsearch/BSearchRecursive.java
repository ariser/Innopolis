package bsearch;

public class BSearchRecursive {
    private static int indexOf(int[] data, int target, int low, int high) {
        Thread.dumpStack();

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
    }

    public static int indexOf(int[] data, int target) {
        return indexOf(data, target, 0, data.length);
    }
}
