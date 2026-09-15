// Last updated: 15/09/2026, 19:58:09
1class Solution {
2    public int maxPalindromes(String s, int k) {
3        int n = s.length();
4        if (k <= 1) return n; // Fast path for k=1
5
6        char[] arr = s.toCharArray();
7        int count = 0;
8        int last = -1;
9
10        for (int i = k - 1; i < n; i++) {
11            // Check for palindrome of length k
12            int l = i - k + 1;
13            if (l > last) {
14                int r = i;
15                while (l < r && arr[l] == arr[r]) {
16                    l++;
17                    r--;
18                }
19                if (l >= r) { // Valid palindrome found
20                    last = i;
21                    count++;
22                    continue; // Skip the k+1 check since we already found an earliest ending
23                }
24            }
25
26            // Check for palindrome of length k + 1
27            l = i - k;
28            if (l > last) {
29                int r = i;
30                while (l < r && arr[l] == arr[r]) {
31                    l++;
32                    r--;
33                }
34                if (l >= r) { // Valid palindrome found
35                    last = i;
36                    count++;
37                }
38            }
39        }
40        
41        return count;
42    }
43}