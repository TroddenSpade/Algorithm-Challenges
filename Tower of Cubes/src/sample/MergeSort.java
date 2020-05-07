package sample;

import java.util.ArrayList;

public class MergeSort {
    private ArrayList<Block> blocks;

    public MergeSort(ArrayList<Block> unsortedBlocks) {
        this.blocks = unsortedBlocks;
        divide(0, unsortedBlocks.size() - 1);
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
        ArrayList<Block> sortedArray = new ArrayList<>();

        int p = start;
        int q = mid + 1;

        while (p <= mid && q <= end) {
            if (blocks.get(p).getW() >= blocks.get(q).getW()) {
                sortedArray.add(blocks.get(p));
                p++;
            } else {
                sortedArray.add(blocks.get(q));
                q++;
            }
        }

        while (p <= mid) {
            sortedArray.add(blocks.get(p));
            p++;
        }

        while (q <= end) {
            sortedArray.add(blocks.get(q));
            q++;
        }

        for (int i = 0; i < sortedArray.size(); i++) {
            blocks.set(i + start, sortedArray.get(i));
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}

