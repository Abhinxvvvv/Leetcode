// Last updated: 22/08/2026, 20:31:21
1class Solution {
2    public boolean checkDivisibility(int n) {
3        int sum = 0;
4        int product = 1;
5        int temp = n;
6        
7        while (temp > 0) {
8            int digit = temp % 10;
9            sum += digit;
10            product *= digit;
11            temp /= 10;
12        }
13        
14        return n % (sum + product) == 0;
15    }
16}