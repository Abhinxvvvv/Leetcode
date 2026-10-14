// Last updated: 14/10/2026, 22:45:20
1class Solution {
2    public String maxSumOfSquares(int num, int sum) {
3        if (sum > 9L * num) {
4            return "";
5        }
6        
7        char[] res = new char[num];
8        int nines = sum / 9;
9        int rem = sum % 9;
10        
11        for (int i = 0; i < nines; i++) {
12            res[i] = '9';
13        }
14        
15        if (nines < num) {
16            res[nines] = (char) ('0' + rem);
17            for (int i = nines + 1; i < num; i++) {
18                res[i] = '0';
19            }
20        }
21        
22        return new String(res);
23    }
24}