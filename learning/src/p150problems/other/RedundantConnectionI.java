package p150problems.other;

import java.util.Arrays;

public class RedundantConnectionI {
    // Initially all nodes are in separate components.
    // For each edge (u, v):
    // If u and v are already in same component → cycle → this edge is redundant
    // Else union(u, v)
    // so make a disjoint set which can do find and union operation

    class DisjointSet {
        int[] ar;

        DisjointSet(int n) {
            ar = new int[n + 1]; // 1-based indexing
            for (int i = 1; i <= n; i++) {
                ar[i] = i;
            }
        }

        int find(int x) {
            if (ar[x] != x) {
                ar[x] = find(ar[x]); // path compression
            }
            return ar[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                ar[rootX] = rootY;
            }
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(edges.length);
        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            if(disjointSet.find(u)!=disjointSet.find(v)){
                disjointSet.union(u,v);
            }else{
                return edge;
            }
        }
        return null;
    }

    public static void main(String[] args){
        int[][]edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(new RedundantConnectionI().findRedundantConnection(edges)));
    }
}
