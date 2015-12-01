package sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    // merge sort
    public static <E extends Comparable> void mergeSort(List<E> source) {
        List<E> tmp = new ArrayList<>(source.size());
        for (int i = 0; i < source.size(); i++) tmp.add(source.get(i));
        mergeSortRecursive(source, 0, source.size(), tmp);
    }

    private static <E extends Comparable> void mergeSortRecursive(List<E> source, int lo, int hi, List<E> tmp) {
        if (hi - lo < 2) return;

        int pivot = (lo + hi) / 2;
        mergeSortRecursive(source, lo, pivot, tmp);
        mergeSortRecursive(source, pivot + 1, hi, tmp);
        merge(source, lo, pivot, hi, tmp);
        copy(tmp, source, lo, hi);
    }

    private static <E extends Comparable> void merge(List<E> source, int lo, int mid, int hi, List<E> tmp) {
        int left = lo;
        int right = mid;

        int index = lo;

        while (left < mid && right < hi) {
            if (source.get(left).compareTo(source.get(right)) <= 0) {
                tmp.add(index, source.get(left));
                left++;
            } else {
                tmp.add(index, source.get(right));
                right++;
            }
            index++;
        }

        while(left < mid) {
            tmp.add(index, source.get(left));
            left++;
        }

        while (right < hi) {
            tmp.add(index, source.get(right));
            right++;
        }
    }

    private static <E extends Comparable> void copy(List<E> src, List<E> dst, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            dst.set(i, src.get(i));
        }
    }

    // qsort
    public static <E extends Comparable> void qsort(List<E> source) {

    }
}
