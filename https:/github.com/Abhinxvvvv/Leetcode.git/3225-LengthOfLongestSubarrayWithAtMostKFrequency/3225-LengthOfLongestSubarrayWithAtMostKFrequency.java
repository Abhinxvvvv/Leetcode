// Last updated: 14/08/2026, 21:32:43
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        // Pre-size the HashMap to prevent slow resizing re-allocations
        Map<Integer, Integer> freqs = new HashMap<>(n);
        
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            int val = nums[right];
            // Extract and update the frequency efficiently
            int count = freqs.getOrDefault(val, 0) + 1;
            freqs.put(val, count);
            
            // If the current element exceeds k, shrink from the left
            while (freqs.get(val) > k) {
                int leftVal = nums[left];
                freqs.put(leftVal, freqs.get(leftVal) - 1);
                left++;
            }
            
            // Calculate current window size
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }
        
        return maxLen;
    }
}