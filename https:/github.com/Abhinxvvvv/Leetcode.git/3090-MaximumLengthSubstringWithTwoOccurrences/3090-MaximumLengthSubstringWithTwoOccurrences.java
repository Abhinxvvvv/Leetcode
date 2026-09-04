// Last updated: 04/09/2026, 11:32:25
class Solution {
    public int maximumLengthSubstring(String s) {
        // Flat array to track frequencies of 26 lowercase English letters
        int[] freq = new int[26];
        int maxLen = 0;
        int left = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            // Map character to array index (0 to 25)
            int rightIdx = s.charAt(right) - 'a';
            freq[rightIdx]++;

            // If validity breaks, slide the left boundary forward until restored
            while (freq[rightIdx] > 2) {
                int leftIdx = s.charAt(left) - 'a';
                freq[leftIdx]--;
                left++;
            }

            // Calculate and track the maximum valid window size
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
        }
        
        return maxLen;
    }
}