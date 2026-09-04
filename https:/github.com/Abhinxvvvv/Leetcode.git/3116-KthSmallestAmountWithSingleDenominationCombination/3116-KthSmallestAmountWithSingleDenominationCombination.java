// Last updated: 04/09/2026, 11:32:24
import java.util.ArrayList;
import java.util.List;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Correctly initialize minCoin to the first element
        long minCoin = coins[0]; 
        for (int c : coins) {
            if (c < minCoin) minCoin = c;
        }
        
        // The absolute maximum value the k-th amount could possibly be
        long high = minCoin * k;
        long low = 1;
        
        List<Long> posList = new ArrayList<>();
        List<Long> negList = new ArrayList<>();
        
        // Pre-generate and prune LCM combinations recursively
        generate(0, 1, 0, coins, high, posList, negList);
        
        // Convert to highly-optimized flat primitive arrays for the inner loop
        long[] pos = new long[posList.size()];
        for (int i = 0; i < posList.size(); i++) pos[i] = posList.get(i);
        
        long[] neg = new long[negList.size()];
        for (int i = 0; i < negList.size(); i++) neg[i] = negList.get(i);
        
        long ans = high;
        
        // Binary search the answer space
        while (low <= high) {
            long mid = (low + high) >>> 1; // High-speed unsigned right shift
            
            // Inclusion-Exclusion Evaluation
            long count = 0;
            for (long p : pos) count += mid / p;
            for (long n : neg) count -= mid / n;
            
            if (count >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private void generate(int idx, long currentLcm, int count, int[] coins, long maxVal, List<Long> pos, List<Long> neg) {
        if (idx == coins.length) {
            if (count > 0) {
                if (count % 2 == 1) pos.add(currentLcm);
                else neg.add(currentLcm);
            }
            return;
        }
        
        // Option 1: Exclude current coin
        generate(idx + 1, currentLcm, count, coins, maxVal, pos, neg);
        
        // Option 2: Include current coin
        long nextLcm = lcm(currentLcm, coins[idx]);
        
        // CRITICAL PRUNING: Only traverse if the LCM doesn't exceed our maximum possible bounds
        if (nextLcm <= maxVal) {
            generate(idx + 1, nextLcm, count + 1, coins, maxVal, pos, neg);
        }
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}