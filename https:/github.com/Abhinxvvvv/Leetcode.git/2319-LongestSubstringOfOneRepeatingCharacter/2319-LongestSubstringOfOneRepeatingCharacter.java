// Last updated: 14/08/2026, 21:33:01
class Solution {
    int[] maxVal;
    int[] prefVal;
    int[] suffVal;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        
        // 1. Allocate flat arrays for the Segment Tree
        maxVal = new int[4 * n];
        prefVal = new int[4 * n];
        suffVal = new int[4 * n];
        arr = s.toCharArray();

        // 2. Build the initial tree
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        // 3. Process each query
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            
            // Only update the tree if the character actually changes
            if (arr[idx] != ch) {
                arr[idx] = ch;
                update(1, 0, n - 1, idx);
            }
            // The root node ALWAYS contains the global maximum
            ans[i] = maxVal[1];
        }

        return ans;
    }

    private void build(int node, int l, int r) {
        // Base case: Leaf node
        if (l == r) {
            maxVal[node] = 1;
            prefVal[node] = 1;
            suffVal[node] = 1;
            return;
        }
        
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        merge(node, l, mid, r);
    }

    private void update(int node, int l, int r, int idx) {
        // Base case: Leaf node reached, no values to merge downstream
        if (l == r) return;
        
        int mid = (l + r) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx);
        } else {
            update(2 * node + 1, mid + 1, r, idx);
        }
        merge(node, l, mid, r);
    }

    private void merge(int node, int l, int mid, int r) {
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        // Default: just take the max from either side
        maxVal[node] = Math.max(maxVal[leftNode], maxVal[rightNode]);
        prefVal[node] = prefVal[leftNode];
        suffVal[node] = suffVal[rightNode];

        // CRITICAL OPTIMIZATION: The Boundary Check
        // If the inner boundary characters match, a larger sequence bridges across them
        if (arr[mid] == arr[mid + 1]) {
            maxVal[node] = Math.max(maxVal[node], suffVal[leftNode] + prefVal[rightNode]);
            
            // If the left child is entirely one character, extend the prefix into the right child
            if (prefVal[leftNode] == mid - l + 1) {
                prefVal[node] += prefVal[rightNode];
            }
            
            // If the right child is entirely one character, extend the suffix into the left child
            if (suffVal[rightNode] == r - mid) {
                suffVal[node] += suffVal[leftNode];
            }
        }
    }
}