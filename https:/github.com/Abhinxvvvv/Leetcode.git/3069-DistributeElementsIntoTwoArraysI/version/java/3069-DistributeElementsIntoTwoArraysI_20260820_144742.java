// Last updated: 20/08/2026, 14:47:42
1import java.util.ArrayList;
2import java.util.List;
3
4class Solution {
5    public int[] resultArray(int[] nums) {
6        List<Integer> arr1 = new ArrayList<>();
7        List<Integer> arr2 = new ArrayList<>();
8        
9        arr1.add(nums[0]);
10        arr2.add(nums[1]);
11        
12        for (int i = 2; i < nums.length; i++) {
13            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
14                arr1.add(nums[i]);
15            } else {
16                arr2.add(nums[i]);
17            }
18        }
19        
20        int[] result = new int[nums.length];
21        int index = 0;
22        
23        for (int num : arr1) {
24            result[index++] = num;
25        }
26        for (int num : arr2) {
27            result[index++] = num;
28        }
29        
30        return result;
31    }
32}