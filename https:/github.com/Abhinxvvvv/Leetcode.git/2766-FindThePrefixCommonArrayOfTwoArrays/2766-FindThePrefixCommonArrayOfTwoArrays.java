// Last updated: 09/08/2026, 00:05:55
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        
        // Sized to n + 1 because the values are 1-indexed (1 to n)
        int[] freq = new int[n + 1];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
            int valA = A[i];
            int valB = B[i];
            
            // Process the element from array A
            freq[valA]++;
            if (freq[valA] == 2) {
                commonCount++;
            }
            
            // Process the element from array B
            freq[valB]++;
            if (freq[valB] == 2) {
                commonCount++;
            }
            
            // Save the running count to our result array
            C[i] = commonCount;
        }
        
        return C;
    }
}
