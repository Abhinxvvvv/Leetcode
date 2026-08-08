// Last updated: 09/08/2026, 00:07:02
class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            int temp = i;
            boolean isGood = false;
            
            while (temp > 0) {
                int digit = temp % 10;
                
                // If it contains an invalid digit, it's permanently ruined. Break early.
                if (digit == 3 || digit == 4 || digit == 7) {
                    isGood = false;
                    break;
                }
                
                // If it contains a rotating digit, it becomes a candidate for a "Good" number.
                // We DON'T break here, because a later digit might be a 3, 4, or 7!
                if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                    isGood = true;
                }
                
                temp /= 10;
            }
            
            if (isGood) {
                count++;
            }
        }
        
        return count;
    }
}
