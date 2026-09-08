// Last updated: 08/09/2026, 19:40:36
1class Solution {
2    public int countCommas(int n) {
3        int count = 0;
4        if (n >= 1000) {
5            count += n - 999;
6        }
7        if (n >= 1000000) {
8            count += n - 999999;
9        }
10        if (n >= 1000000000) {
11            count += n - 999999999;
12        }
13        return count;
14    }
15}