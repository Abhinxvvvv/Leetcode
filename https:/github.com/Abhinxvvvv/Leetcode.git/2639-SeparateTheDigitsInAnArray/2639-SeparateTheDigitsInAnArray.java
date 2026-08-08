// Last updated: 09/08/2026, 00:06:03
class Solution {
    public int[] separateDigits(int[] nums) {
        // 1. Calculate the exact size needed for our answer array
        // Using raw comparisons is drastically faster than converting to strings or using logarithms
        int totalDigits = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num >= 100000) totalDigits += 6;
            else if (num >= 10000) totalDigits += 5;
            else if (num >= 1000) totalDigits += 4;
            else if (num >= 100) totalDigits += 3;
            else if (num >= 10) totalDigits += 2;
            else totalDigits += 1;
        }
        
        // Exact allocation. Zero Garbage Collection overhead.
        int[] ans = new int[totalDigits];
        int idx = totalDigits - 1;
        
        // 2. The Backward Filling Trick
        // Loop backwards through the input, and write backwards into the answer array
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            while (num > 0) {
                ans[idx--] = num % 10;
                num /= 10;
            }
        }
        
        return ans;
    }
}
