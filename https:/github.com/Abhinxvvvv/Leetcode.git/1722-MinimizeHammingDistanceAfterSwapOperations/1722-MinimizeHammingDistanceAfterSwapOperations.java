// Last updated: 04/09/2026, 11:33:51
import java.util.*;

class Solution {
    int[] parent;
    
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1]);
        }
        
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(i);
            map.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> counts = map.get(root);
            counts.put(source[i], counts.getOrDefault(source[i], 0) + 1);
        }
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            int root = find(i);
            Map<Integer, Integer> counts = map.get(root);
            if (counts.getOrDefault(target[i], 0) > 0) {
                counts.put(target[i], counts.get(target[i]) - 1);
            } else {
                res++;
            }
        }
        return res;
    }
    
    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}
