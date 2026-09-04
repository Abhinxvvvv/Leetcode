// Last updated: 04/09/2026, 11:32:42
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        // Flat array to store the indices of '1's
        int[] ones = new int[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }
        
        // If there aren't enough 1s, return empty string
        if (count < k) {
            return "";
        }
        
        int minLen = Integer.MAX_VALUE;
        String bestStr = "";
        
        // Check exact bounded lengths directly via indices
        for (int i = 0; i <= count - k; i++) {
            int start = ones[i];
            int end = ones[i + k - 1];
            int len = end - start + 1;
            
            if (len < minLen) {
                minLen = len;
                bestStr = s.substring(start, end + 1);
            } else if (len == minLen) {
                String sub = s.substring(start, end + 1);
                // Lexicographical comparison for tie-breakers
                if (sub.compareTo(bestStr) < 0) {
                    bestStr = sub;
                }
            }
        }
        
        return bestStr;
    }
}