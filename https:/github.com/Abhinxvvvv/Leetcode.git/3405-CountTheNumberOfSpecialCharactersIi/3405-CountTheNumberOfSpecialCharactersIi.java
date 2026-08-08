// Last updated: 09/08/2026, 00:05:37
class Solution {
    public int numberOfSpecialChars(String word) {
        // Fast flat array lookups for 26 English characters
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        
        // Initialize with -1 to indicate unseen status
        for (int i = 0; i < 26; i++) {
            lastLower[i] = -1;
            firstUpper[i] = -1;
        }
        
        int len = word.length();
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                // Continuously overwrite to track the LAST lowercase position
                lastLower[c - 'a'] = i;
            } else {
                int idx = c - 'A';
                // Lock down only the FIRST uppercase position
                if (firstUpper[idx] == -1) {
                    firstUpper[idx] = i;
                }
            }
        }
        
        int specialCount = 0;
        // Verify conditions linearly in O(1) constant time
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]) {
                specialCount++;
            }
        }
        
        return specialCount;
    }
}
