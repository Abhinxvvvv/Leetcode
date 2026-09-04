// Last updated: 04/09/2026, 11:32:28
import java.util.HashSet;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>(arr1.length * 4);
        
        // 1. Store all unique numerical prefixes of arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }
        
        int maxLength = 0;
        
        // 2. Scan arr2 to locate the longest matching prefix
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    // Calculate digit length mathematically without string overhead
                    int len = 0;
                    int temp = num;
                    while (temp > 0) {
                        len++;
                        temp /= 10;
                    }
                    
                    if (len > maxLength) {
                        maxLength = len;
                    }
                    break; // Since we scan from largest prefix down, we short-circuit early
                }
                num /= 10;
            }
        }
        
        return maxLength;
    }
}
