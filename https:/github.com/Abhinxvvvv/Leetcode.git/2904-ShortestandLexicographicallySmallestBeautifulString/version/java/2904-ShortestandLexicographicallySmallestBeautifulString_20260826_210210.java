// Last updated: 26/08/2026, 21:02:10
1class Solution {
2    public String shortestBeautifulSubstring(String s, int k) {
3        int n = s.length();
4        // Flat array to store the indices of '1's
5        int[] ones = new int[n];
6        int count = 0;
7        
8        for (int i = 0; i < n; i++) {
9            if (s.charAt(i) == '1') {
10                ones[count++] = i;
11            }
12        }
13        
14        // If there aren't enough 1s, return empty string
15        if (count < k) {
16            return "";
17        }
18        
19        int minLen = Integer.MAX_VALUE;
20        String bestStr = "";
21        
22        // Check exact bounded lengths directly via indices
23        for (int i = 0; i <= count - k; i++) {
24            int start = ones[i];
25            int end = ones[i + k - 1];
26            int len = end - start + 1;
27            
28            if (len < minLen) {
29                minLen = len;
30                bestStr = s.substring(start, end + 1);
31            } else if (len == minLen) {
32                String sub = s.substring(start, end + 1);
33                // Lexicographical comparison for tie-breakers
34                if (sub.compareTo(bestStr) < 0) {
35                    bestStr = sub;
36                }
37            }
38        }
39        
40        return bestStr;
41    }
42}