// Last updated: 04/09/2026, 11:31:56
import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];
        
        // 1. Map 2D coordinates to a 1D perimeter coordinate
        for (int i = 0; i < n; i++) {
            long x = points[i][0];
            long y = points[i][1];
            
            if (y == 0) {
                arr[i] = x;
            } else if (x == side) {
                arr[i] = side + y;
            } else if (y == side) {
                arr[i] = 3L * side - x;
            } else {
                arr[i] = 4L * side - y;
            }
        }
        
        Arrays.sort(arr);
        
        // 2. Extend the array to handle the circular wrap-around seamlessly
        long[] extArr = new long[2 * n];
        for (int i = 0; i < n; i++) {
            extArr[i] = arr[i];
            extArr[i + n] = arr[i] + 4L * side;
        }
        
        // 3. Binary Search for the optimal minimum distance D
        long low = 1;
        long high = side;
        long ans = 0;
        
        int[] nxt = new int[2 * n];
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Precompute the "next" valid point we can jump to for this distance
            int j = 0;
            for (int i = 0; i < 2 * n; i++) {
                while (j < 2 * n && extArr[j] - extArr[i] < mid) {
                    j++;
                }
                nxt[i] = j;
            }
            
            boolean possible = false;
            
            // Try starting the sequence from every possible point
            for (int i = 0; i < n; i++) {
                int curr = i;
                int count = 1;
                
                // Jump forward (k - 1) times
                while (count < k && curr < 2 * n) {
                    curr = nxt[curr];
                    count++;
                }
                
                // If we successfully picked k points AND the gap wrapping around 
                // to the start point is also >= mid, this distance is valid!
                if (count == k && curr < i + n && extArr[i] + 4L * side - extArr[curr] >= mid) {
                    possible = true;
                    break;
                }
            }
            
            if (possible) {
                ans = mid;
                low = mid + 1;  // Try to find a larger minimum distance
            } else {
                high = mid - 1; // Distance is too large, reduce it
            }
        }
        
        return (int) ans;
    }
}
