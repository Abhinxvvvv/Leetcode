// Last updated: 10/09/2026, 23:22:44
1/**
2 * Definition for a binary tree node.
3 * public class TreeNode {
4 *     int val;
5 *     TreeNode left;
6 *     TreeNode right;
7 *     TreeNode() {}
8 *     TreeNode(int val) { this.val = val; }
9 *     TreeNode(int val, TreeNode left, TreeNode right) {
10 *         this.val = val;
11 *         this.left = left;
12 *         this.right = right;
13 *     }
14 * }
15 */
16class Solution {
17    private int matches = 0;
18
19    public int averageOfSubtree(TreeNode root) {
20        dfs(root);
21        return matches;
22    }
23
24    private int dfs(TreeNode node) {
25        if (node == null) {
26            return 0;
27        }
28
29        // Post-order traversal
30        int left = dfs(node.left);
31        int right = dfs(node.right);
32
33        // Unpack left child data
34        int leftCount = left & 0xFFF; // Extract bottom 12 bits
35        int leftSum = left >>> 12;    // Extract top 20 bits
36
37        // Unpack right child data
38        int rightCount = right & 0xFFF;
39        int rightSum = right >>> 12;
40
41        // Calculate current subtree totals
42        int currentCount = leftCount + rightCount + 1;
43        int currentSum = leftSum + rightSum + node.val;
44
45        // Check if the current node satisfies the condition
46        if (currentSum / currentCount == node.val) {
47            matches++;
48        }
49
50        // Pack sum and count back into a single integer and return
51        return (currentSum << 12) | currentCount;
52    }
53}