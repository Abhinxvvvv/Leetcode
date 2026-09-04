// Last updated: 04/09/2026, 11:31:47
import java.util.*;

class Solution {
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        int LOG = 18;
        int[][] up = new int[n][LOG];
        int[] depth = new int[n];
        long[] dist = new long[n]; 

        // BFS to initialize depth, distance from root, and binary lifting arrays
        int[] q = new int[n];
        int head = 0, tail = 0;
        q[tail++] = 0;
        boolean[] vis = new boolean[n];
        vis[0] = true;

        while (head < tail) {
            int u = q[head++];
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                if (!vis[v]) {
                    vis[v] = true;
                    depth[v] = depth[u] + 1;
                    dist[v] = dist[u] + w;
                    up[v][0] = u;
                    for (int i = 1; i < LOG; i++) {
                        up[v][i] = up[up[v][i-1]][i-1];
                    }
                    q[tail++] = v;
                }
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int w = queries[i][2];
            
            // Calculate pairwise distances
            long d1 = getDist(u, v, up, depth, dist, LOG);
            long d2 = getDist(v, w, up, depth, dist, LOG);
            long d3 = getDist(w, u, up, depth, dist, LOG);
            
            // Formula requires sum cast to long to avoid 32-bit int overflow during addition
            ans[i] = (int) ((d1 + d2 + d3) / 2);
        }
        return ans;
    }

    private int getLCA(int u, int v, int[][] up, int[] depth, int LOG) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) == 1) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    private long getDist(int u, int v, int[][] up, int[] depth, long[] dist, int LOG) {
        int lca = getLCA(u, v, up, depth, LOG);
        return dist[u] + dist[v] - 2 * dist[lca];
    }
}