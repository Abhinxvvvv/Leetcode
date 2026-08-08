// Last updated: 09/08/2026, 00:06:54
import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        // 1. Group indices with the same value using a pre-sized HashMap
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        // 2. BFS Setup using optimized raw primitive structures where possible
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                // Target reached!
                if (curr == n - 1) return steps;

                // Grab teleportation jumps for identical values
                List<Integer> sameValues = graph.get(arr[curr]);
                if (sameValues != null) {
                    for (int nextIdx : sameValues) {
                        if (!visited[nextIdx]) {
                            visited[nextIdx] = true;
                            queue.offer(nextIdx);
                        }
                    }
                    // CRITICAL OPTIMIZATION: Clear the list to prevent O(N^2) redundant scanning
                    graph.remove(arr[curr]);
                }

                // Adjacent step: Right (i + 1)
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // Adjacent step: Left (i - 1)
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }
            }
            steps++;
        }

        return -1;
    }
}
