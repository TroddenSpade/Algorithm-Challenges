package sample;

import java.util.ArrayList;

class Graph {

    int vertices;
    ArrayList[] edge;

    Graph(int vertices) {
        this.vertices = vertices;
        edge = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            edge[i] = new ArrayList<>();
        }
    }

    void addEdge(int a, int b) {
        edge[a].add(b);
    }

    void dfs(int node, ArrayList<Integer>[] adj, int[] dp,
             boolean[] visited, int[] bestChild) {
        visited[node] = true;

        for (int i = 0; i < adj[node].size(); i++) {
            if (!visited[adj[node].get(i)])
                dfs(adj[node].get(i), adj, dp, visited, bestChild);
            if (dp[node] < 1 + dp[adj[node].get(i)]) {
                dp[node] = 1 + dp[adj[node].get(i)];
                bestChild[node] = adj[node].get(i);
            }
        }
    }

    ArrayList<Integer> longestPath() {
        int n = vertices;
        ArrayList[] neighbours = edge;
        ArrayList<Integer> map = new ArrayList<>();

        int[] dp = new int[n];
        int[] bestChild = new int[n];

        for (int i = 0; i < n; i++) {
            bestChild[i] = -1;
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, neighbours, dp, visited, bestChild);
        }

        int res = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > res) {
                res = dp[i];
                start = i;
            }
        }
        while (start != -1) {
            map.add(start);
            start = bestChild[start];
        }

        return map;
    }

    public void create(ArrayList<Block> blocks) {
        for (int i = 0; i < blocks.size(); i++) {
            for (int j = i + 1; j < blocks.size(); j++) {
                if (blocks.get(i).getTop() == blocks.get(j).getBottom() &&
                        blocks.get(i).getW() > blocks.get(j).getW()) {
                    this.addEdge(i, j);
//                    System.out.println("e : " + i + "," + j);
                }
            }
        }
    }
}
