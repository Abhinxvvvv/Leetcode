// Last updated: 09/08/2026, 00:06:12
class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;
        
        // 1. Find the maximum distance from the FIRST house (index 0)
        // We iterate backwards from the end of the array.
        for (int i = n - 1; i >= 0; i--) {
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i); // Distance is i - 0 = i
                break;
            }
        }
        
        // 2. Find the maximum distance from the LAST house (index n-1)
        // We iterate forwards from the start of the array.
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
                break;
            }
        }
        
        return maxDist;
    }
}
