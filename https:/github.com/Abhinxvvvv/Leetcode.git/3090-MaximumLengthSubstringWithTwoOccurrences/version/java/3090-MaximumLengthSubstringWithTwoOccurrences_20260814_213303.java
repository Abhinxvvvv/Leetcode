// Last updated: 14/08/2026, 21:33:03
1class Solution {
2    public int maximumLengthSubstring(String s) {
3        // Flat array to track frequencies of 26 lowercase English letters
4        int[] freq = new int[26];
5        int maxLen = 0;
6        int left = 0;
7        int n = s.length();
8
9        for (int right = 0; right < n; right++) {
10            // Map character to array index (0 to 25)
11            int rightIdx = s.charAt(right) - 'a';
12            freq[rightIdx]++;
13
14            // If validity breaks, slide the left boundary forward until restored
15            while (freq[rightIdx] > 2) {
16                int leftIdx = s.charAt(left) - 'a';
17                freq[leftIdx]--;
18                left++;
19            }
20
21            // Calculate and track the maximum valid window size
22            int currentLen = right - left + 1;
23            if (currentLen > maxLen) {
24                maxLen = currentLen;
25            }
26        }
27        
28        return maxLen;
29    }
30}