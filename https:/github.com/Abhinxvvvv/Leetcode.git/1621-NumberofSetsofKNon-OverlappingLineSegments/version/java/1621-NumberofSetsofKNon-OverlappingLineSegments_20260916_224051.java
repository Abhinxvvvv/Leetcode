// Last updated: 16/09/2026, 22:40:51
1class Solution {
2    public int numberOfSets(int n, int k) {
3        int mod = 1000000007;
4        int N = n + k - 1;
5        int K = 2 * k;
6        
7        if (K > N) {
8            return 0;
9        }
10        
11        long num = 1;
12        long den = 1;
13        
14        for (int i = 1; i <= K; i++) {
15            num = (num * (N - i + 1)) % mod;
16            den = (den * i) % mod;
17        }
18        
19        return (int) ((num * power(den, mod - 2, mod)) % mod);
20    }
21    
22    private long power(long base, long exp, int mod) {
23        long res = 1;
24        base %= mod;
25        while (exp > 0) {
26            if ((exp & 1) == 1) {
27                res = (res * base) % mod;
28            }
29            base = (base * base) % mod;
30            exp >>= 1;
31        }
32        return res;
33    }
34}