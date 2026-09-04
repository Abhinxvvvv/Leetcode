// Last updated: 04/09/2026, 11:33:14
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        
        // Single pass to find the indices of min and max elements
        int minIdx = 0;
        int maxIdx = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            } else if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        
        // Identify which index comes first and which comes second
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        
        // Scenario 1: Remove both from the front
        int bothFront = j + 1;
        
        // Scenario 2: Remove both from the back
        int bothBack = n - i;
        
        // Scenario 3: Remove the first one from the front, and the second one from the back
        int frontAndBack = (i + 1) + (n - j);
        
        // Return the most optimal path
        return Math.min(Math.min(bothFront, bothBack), frontAndBack);
    }
}