// Last updated: 20/09/2026, 12:38:57
1class Solution {
2    public int reverseDegree(String s) {
3        int result = 0;
4        char[] arr = s.toCharArray();
5        for (int i = 0; i < arr.length; i++) {
6            result += ('z' - arr[i] + 1) * (i + 1);
7        }
8        return result;
9    }
10}