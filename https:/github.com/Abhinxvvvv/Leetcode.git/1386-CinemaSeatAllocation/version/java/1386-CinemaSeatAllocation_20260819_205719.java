// Last updated: 19/08/2026, 20:57:19
1import java.util.HashMap;
2import java.util.Map;
3
4class Solution {
5    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
6        Map<Integer, Integer> map = new HashMap<>();
7        
8        for (int[] seat : reservedSeats) {
9            if (seat[1] >= 2 && seat[1] <= 9) {
10                map.put(seat[0], map.getOrDefault(seat[0], 0) | (1 << seat[1]));
11            }
12        }
13        
14        int ans = 2 * (n - map.size());
15        
16        for (int val : map.values()) {
17            boolean left = (val & 60) == 0;
18            boolean right = (val & 960) == 0;
19            boolean middle = (val & 240) == 0;
20            
21            if (left && right) {
22                ans += 2;
23            } else if (left || right || middle) {
24                ans += 1;
25            }
26        }
27        
28        return ans;
29    }
30}