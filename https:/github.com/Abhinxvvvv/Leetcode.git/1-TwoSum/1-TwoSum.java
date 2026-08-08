// Last updated: 09/08/2026, 00:07:40
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store the number and its index
        Map<Integer, Integer> numMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If the complement exists in our map, we found the pair
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }
            
            // Otherwise, add the current number and its index to the map
            numMap.put(nums[i], i);
        }
        
        return new int[]{}; // Should not be reached based on problem constraints
    }
}