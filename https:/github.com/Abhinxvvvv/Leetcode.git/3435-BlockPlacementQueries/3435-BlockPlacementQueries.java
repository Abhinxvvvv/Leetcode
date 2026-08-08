// Last updated: 09/08/2026, 00:05:32
import java.util.*;

class Solution {
    // High-speed point-update, range-max Fenwick Tree
    static class FenwickTree {
        int[] tree;
        int size;

        public FenwickTree(int n) {
            this.size = n;
            this.tree = new int[n + 1];
        }

        public void update(int i, int val) {
            while (i <= size) {
                tree[i] = Math.max(tree[i], val);
                i += i & -i;
            }
        }

        public int query(int i) {
            int maxVal = 0;
            while (i > 0) {
                maxVal = Math.max(maxVal, tree[i]);
                i -= i & -i;
            }
            return maxVal;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        // The problem bounds 'x' up to min(50000, 3 * queries.length)
        int maxCoord = 50005;
        
        // Keep track of final obstacles using a sorted structure for step calculations
        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(maxCoord);

        // Gather all final obstacles from Type 1 queries
        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
            }
        }

        FenwickTree bit = new FenwickTree(maxCoord);
        
        // Seed the Fenwick Tree with initial gaps between all adjacent obstacles
        int prev = 0;
        for (int curr : obstacles) {
            if (curr != 0) {
                bit.update(curr, curr - prev);
                prev = curr;
            }
        }

        List<Boolean> reversedAnswers = new ArrayList<>();

        // Process all operations completely in reverse
        for (int i = queries.length - 1; i >= 0; i--) {
            int[] q = queries[i];
            int type = q[0];
            int x = q[1];

            if (type == 1) {
                // TRAVELING BACK IN TIME: Remove obstacle at x, merging adjacent gaps
                obstacles.remove(x);
                int prevObstacle = obstacles.lower(x);
                int nextObstacle = obstacles.higher(x);
                
                // Update the gap value corresponding to the next obstacle boundary
                bit.update(nextObstacle, nextObstacle - prevObstacle);
            } else {
                int sz = q[2];
                int prevObstacle = obstacles.floor(x);
                
                // Check if block fits either inside the historical max gaps before prevObstacle, 
                // or within the remaining boundary gap up to x
                boolean fits = (bit.query(prevObstacle) >= sz) || (x - prevObstacle >= sz);
                reversedAnswers.add(fits);
            }
        }

        // Restore correct order of responses
        Collections.reverse(reversedAnswers);
        return reversedAnswers;
    }
}
