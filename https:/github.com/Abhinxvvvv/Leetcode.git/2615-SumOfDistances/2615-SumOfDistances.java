// Last updated: 04/09/2026, 11:32:59
import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        // Map to store lists of indices for each unique number
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        for (List<Integer> idxs : map.values()) {
            int k = idxs.size();
            if (k <= 1) continue;
            
            long suffixSum = 0;
            for (int idx : idxs) {
                suffixSum += idx;
            }
            
            long prefixSum = 0;
            for (int m = 0; m < k; m++) {
                long idx = idxs.get(m);
                
                // Remove current index from suffix sum
                suffixSum -= idx;
                
                // Calculate distances from elements on the left and right
                long leftDist = m * idx - prefixSum;
                long rightDist = suffixSum - (k - 1 - m) * idx;
                
                ans[(int) idx] = leftDist + rightDist;
                
                // Add current index to prefix sum for the next iteration
                prefixSum += idx;
            }
        }
        
        return ans;
    }
}
