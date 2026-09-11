// Last updated: 11/09/2026, 22:52:50
1class Solution {
2    public int totalNumbers(int[] digits) {
3        int[] count = new int[10];
4        for (int d : digits) {
5            count[d]++;
6        }
7        
8        int res = 0;
9        for (int i = 1; i <= 9; i++) {
10            if (count[i] == 0) continue;
11            count[i]--;
12            
13            for (int j = 0; j <= 9; j++) {
14                if (count[j] == 0) continue;
15                count[j]--;
16                
17                for (int k = 0; k <= 8; k += 2) {
18                    if (count[k] > 0) {
19                        res++;
20                    }
21                }
22                
23                count[j]++;
24            }
25            
26            count[i]++;
27        }
28        
29        return res;
30    }
31}