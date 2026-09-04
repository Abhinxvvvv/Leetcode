// Last updated: 04/09/2026, 11:34:14
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            if (seat[1] >= 2 && seat[1] <= 9) {
                map.put(seat[0], map.getOrDefault(seat[0], 0) | (1 << seat[1]));
            }
        }
        
        int ans = 2 * (n - map.size());
        
        for (int val : map.values()) {
            boolean left = (val & 60) == 0;
            boolean right = (val & 960) == 0;
            boolean middle = (val & 240) == 0;
            
            if (left && right) {
                ans += 2;
            } else if (left || right || middle) {
                ans += 1;
            }
        }
        
        return ans;
    }
}