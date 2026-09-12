// Last updated: 12/09/2026, 20:18:49
1class Solution {
2    public int countSpecialIntegers(int[] nums) {
3        Map<Integer,List<Integer>> map=new HashMap<>();
4        for(int i=0; i<nums.length;i++) {
5            map.computeIfAbsent(nums[i], k->new ArrayList<>()).add(i);
6        }
7
8        int count =0;
9        for(List<Integer>indices : map.values()){ 
10            if (indices.size()==3){
11                if (indices.get(1) - indices.get(0) == indices.get(2) - indices.get(1)){
12                    count++;
13                }
14            }
15        }
16        return count;
17    }
18}