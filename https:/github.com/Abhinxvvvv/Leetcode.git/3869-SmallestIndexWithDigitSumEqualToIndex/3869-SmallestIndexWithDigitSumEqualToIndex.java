// Last updated: 09/08/2026, 00:05:06
class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (getDigitSum(nums[i]) == i) {
                return i; // First match will naturally be the smallest index
            }
        }
        return -1;
    }
    
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;  
            num /= 10;        
        }
        return sum;
    }
}