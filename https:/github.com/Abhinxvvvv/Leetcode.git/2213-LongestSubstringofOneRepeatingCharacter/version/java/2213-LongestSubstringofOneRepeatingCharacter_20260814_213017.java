// Last updated: 14/08/2026, 21:30:17
1class Solution {
2    int[] maxVal;
3    int[] prefVal;
4    int[] suffVal;
5    char[] arr;
6
7    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
8        int n = s.length();
9        
10        // 1. Allocate flat arrays for the Segment Tree
11        maxVal = new int[4 * n];
12        prefVal = new int[4 * n];
13        suffVal = new int[4 * n];
14        arr = s.toCharArray();
15
16        // 2. Build the initial tree
17        build(1, 0, n - 1);
18
19        int k = queryIndices.length;
20        int[] ans = new int[k];
21
22        // 3. Process each query
23        for (int i = 0; i < k; i++) {
24            int idx = queryIndices[i];
25            char ch = queryCharacters.charAt(i);
26            
27            // Only update the tree if the character actually changes
28            if (arr[idx] != ch) {
29                arr[idx] = ch;
30                update(1, 0, n - 1, idx);
31            }
32            // The root node ALWAYS contains the global maximum
33            ans[i] = maxVal[1];
34        }
35
36        return ans;
37    }
38
39    private void build(int node, int l, int r) {
40        // Base case: Leaf node
41        if (l == r) {
42            maxVal[node] = 1;
43            prefVal[node] = 1;
44            suffVal[node] = 1;
45            return;
46        }
47        
48        int mid = (l + r) / 2;
49        build(2 * node, l, mid);
50        build(2 * node + 1, mid + 1, r);
51        merge(node, l, mid, r);
52    }
53
54    private void update(int node, int l, int r, int idx) {
55        // Base case: Leaf node reached, no values to merge downstream
56        if (l == r) return;
57        
58        int mid = (l + r) / 2;
59        if (idx <= mid) {
60            update(2 * node, l, mid, idx);
61        } else {
62            update(2 * node + 1, mid + 1, r, idx);
63        }
64        merge(node, l, mid, r);
65    }
66
67    private void merge(int node, int l, int mid, int r) {
68        int leftNode = 2 * node;
69        int rightNode = 2 * node + 1;
70
71        // Default: just take the max from either side
72        maxVal[node] = Math.max(maxVal[leftNode], maxVal[rightNode]);
73        prefVal[node] = prefVal[leftNode];
74        suffVal[node] = suffVal[rightNode];
75
76        // CRITICAL OPTIMIZATION: The Boundary Check
77        // If the inner boundary characters match, a larger sequence bridges across them
78        if (arr[mid] == arr[mid + 1]) {
79            maxVal[node] = Math.max(maxVal[node], suffVal[leftNode] + prefVal[rightNode]);
80            
81            // If the left child is entirely one character, extend the prefix into the right child
82            if (prefVal[leftNode] == mid - l + 1) {
83                prefVal[node] += prefVal[rightNode];
84            }
85            
86            // If the right child is entirely one character, extend the suffix into the left child
87            if (suffVal[rightNode] == r - mid) {
88                suffVal[node] += suffVal[leftNode];
89            }
90        }
91    }
92}