// Last updated: 07/09/2026, 17:09:16
1class Solution {
2    public int distinctSubseqII(String s) {
3        int MOD = 1000000007;
4        
5        // Array to store the number of distinct subsequences ending with each letter ('a'-'z')
6        int[] ends = new int[26];
7        int total = 0;
8        
9        // Convert to char array for bare-metal memory access, bypassing .charAt() overhead
10        char[] chars = s.toCharArray();
11        
12        for (int i = 0; i < chars.length; i++) {
13            int idx = chars[i] - 'a';
14            
15            // The new subsequences we can form are all existing ones + the character by itself (total + 1).
16            // However, we must subtract the subsequences that already ended with this character to avoid duplicates.
17            int added = (total + 1 - ends[idx]) % MOD;
18            
19            // Handle negative modulo results in Java
20            if (added < 0) {
21                added += MOD;
22            }
23            
24            // Update total and the ends array with the freshly added subsequences
25            total = (total + added) % MOD;
26            ends[idx] = (ends[idx] + added) % MOD;
27        }
28        
29        return total;
30    }
31}