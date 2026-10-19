// Last updated: 19/10/2026, 18:17:36
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public List<Integer> targetIndices(int[] nums, int target) {
6        int lessThanCount = 0;
7        int targetCount = 0;
8        
9        // Single pass to count strictly smaller elements and the target frequency
10        for (int num : nums) {
11            if (num < target) {
12                lessThanCount++;
13            } else if (num == target) {
14                targetCount++;
15            }
16        }
17        
18        // Pre-allocate the exact capacity to avoid dynamic array resizing overhead
19        List<Integer> result = new ArrayList<>(targetCount);
20        
21        // The target elements will sit perfectly consecutively after all the smaller elements
22        for (int i = 0; i < targetCount; i++) {
23            result.add(lessThanCount + i);
24        }
25        
26        return result;
27    }
28}