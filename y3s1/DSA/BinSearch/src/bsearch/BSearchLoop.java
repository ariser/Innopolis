package bsearch;

public class BSearchLoop {
    private static int indexOf(int[] data, int target, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (target == data[mid]) {
                return mid;
            }

            if (target < data[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static int indexOf(int[] data, int target) {
        return indexOf(data, target, 0, data.length);
    }
}
