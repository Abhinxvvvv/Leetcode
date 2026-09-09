// Last updated: 09/09/2026, 20:42:00
1class Solution {
2    public long countCommas(long n) {
3        long count = 0;
4        if (n >= 1000L) {
5            count += n - 999L;
6        }
7        if (n >= 1000000L) {
8            count += n - 999999L;
9        }
10        if (n >= 1000000000L) {
11            count += n - 999999999L;
12        }
13        if (n >= 1000000000000L) {
14            count += n - 999999999999L;
15        }
16        if (n >= 1000000000000000L) {
17            count += n - 999999999999999L;
18        }
19        return count;
20    }
21}