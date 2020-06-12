package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Kruskal {
    private ArrayList<Integer>[] subsets;
    private ArrayList<Edge> F;

    Kruskal(int n, int m, ArrayList<Edge> edges) {
        int i, j;
        ArrayList<Integer> p, q;
        Edge e;


        Comparator<Edge> comparator = (e1, e2) -> {
            return e1.getWeight() - e2.getWeight();
        };
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(comparator);
        for (int k = 0; k < n; k++) {
            edgePriorityQueue.add(edges.get(k));
        }

        F = new ArrayList<Edge>();

        initial(n);

        while (F.size() < n - 1) {
            e = edgePriorityQueue.remove();
            i = e.getI();
            j = e.getJ();
            p = find(i);
            q = find(j);

            assert p != null;
            if(!p.equals(q)){
                merge(p,q);
                F.add(e);
            }
        }

    }

    private void merge(ArrayList<Integer> p, ArrayList<Integer> q) {
        p.addAll(q);
        q.clear();
    }


    private void initial(int n) {
        subsets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            this.subsets[i] = new ArrayList<Integer>();
            this.subsets[i].add(i);
        }
    }

    private ArrayList<Integer> find(int i){
        for (ArrayList<Integer> subset : subsets) {
            if(subset == null) continue;
            if (subset.contains(i)) return subsets[i];
        }
        return null;
    }

    public ArrayList<Edge> getF() {
        return F;
    }
}
