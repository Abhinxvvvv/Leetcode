// Last updated: 12/09/2026, 20:30:38
1class Solution {
2    public int countSpecialIntegers(int[] nums) {
3        Map<Integer,List<Integer>>map = new HashMap<>();
4        for (int i =0; i<nums.length;i++){
5            map.computeIfAbsent(nums[i], K-> new ArrayList<>()).add(i);
6        }
7
8        int count =0;
9        for (List<Integer>pos : map.values()){
10            if (pos.size()<3) continue;
11            int diff = pos.get(1) - pos.get(0), i=2;
12            while (i<pos.size()&&pos.get(i) - pos.get(i-1)==diff)i++;
13            if(i==pos.size()) count++;
14        
15        }
16        return count;
17    }
18}