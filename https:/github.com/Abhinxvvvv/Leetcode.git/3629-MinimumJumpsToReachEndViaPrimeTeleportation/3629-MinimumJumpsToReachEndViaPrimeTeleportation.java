// Last updated: 04/09/2026, 11:31:36
class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Find the maximum value to properly bound our Sieve and Head arrays
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxVal) maxVal = nums[i];
        }
        
        // 1. Memory-crushing Adjacency List (Replaces heavy HashMaps)
        int[] head = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) head[i] = -1;
        
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            next[i] = head[val];
            head[val] = i;
        }
        
        // 2. Sieve of Eratosthenes
        boolean[] notPrime = new boolean[maxVal + 1];
        notPrime[0] = true;
        if (maxVal >= 1) notPrime[1] = true;
        
        for (int p = 2; p * p <= maxVal; p++) {
            if (!notPrime[p]) {
                for (int i = p * p; i <= maxVal; i += p) {
                    notPrime[i] = true;
                }
            }
        }
        
        // 3. Raw Array Queue for maximum BFS speed
        int[] q = new int[n];
        int front = 0, tail = 0;
        boolean[] visited = new boolean[n];
        
        q[tail++] = 0;
        visited[0] = true;
        int jumps = 0;
        
        while (front < tail) {
            int size = tail - front;
            
            for (int i = 0; i < size; i++) {
                int curr = q[front++];
                
                // Destination reached
                if (curr == n - 1) return jumps;
                
                // Adjacent step: Right
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    q[tail++] = curr + 1;
                }
                
                // Adjacent step: Left
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    q[tail++] = curr - 1;
                }
                
                // Prime Teleportation Step
                int val = nums[curr];
                if (!notPrime[val]) {
                    // OPTIMIZATION: Mark prime as non-prime so we never process it twice
                    notPrime[val] = true; 
                    
                    // Instantly iterate through all multiples up to maxVal
                    for (int m = val; m <= maxVal; m += val) {
                        for (int j = head[m]; j != -1; j = next[j]) {
                            if (!visited[j]) {
                                visited[j] = true;
                                q[tail++] = j;
                            }
                        }
                    }
                }
            }
            jumps++;
        }
        
        return -1;
    }
}
