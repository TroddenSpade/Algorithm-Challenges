package sample;

import java.util.ArrayList;

public class MergeSort {
    private ArrayList<Wall> skylines;

    public MergeSort(ArrayList<Wall> unsortedSkylines) {
        this.skylines = unsortedSkylines;
        divide(0, unsortedSkylines.size() - 1);
    }

    public void divide(int start, int end) {
        if (start < end && (end - start) >= 1) {
            int mid = start + (end - start) / 2;
            divide(start, mid);
            divide(mid + 1, end);
            merge(start, mid, end);
        }
    }

    public void merge(int start, int mid, int end) {
        ArrayList<Wall> sortedArray = new ArrayList<>();

        int p = start;
        int q = mid + 1;

        while (p <= mid && q <= end) {
            if (skylines.get(p).getX() <= skylines.get(q).getX()) {
                sortedArray.add(skylines.get(p));
                p++;
            } else {
                sortedArray.add(skylines.get(q));
                q++;
            }
        }

        while (p <= mid) {
            sortedArray.add(skylines.get(p));
            p++;
        }

        while (q <= end) {
            sortedArray.add(skylines.get(q));
            q++;
        }

        for (int i = 0; i < sortedArray.size(); i++) {
            skylines.set(i + start, sortedArray.get(i));
        }
    }

    public ArrayList<Wall> getSortedWalls() {
        return skylines;
    }
}
