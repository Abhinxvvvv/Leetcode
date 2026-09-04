// Last updated: 04/09/2026, 11:31:22
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] cnt = new int[26];
        
        // 1. Map occurrences and validate if a palindrome is possible
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                if (oddChar != -1) return "";
                oddChar = i;
            }
        }
        
        int[] leftCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            leftCnt[i] = cnt[i] / 2;
        }
        
        // 2. Find the maximum prefix length we can perfectly match with target's left half
        int maxMatch = 0;
        int[] curFreq = new int[26];
        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';
            if (leftCnt[c] > curFreq[c]) {
                curFreq[c]++;
                maxMatch++;
            } else {
                break;
            }
        }
        
        // 3. Perfect Match Case 
        if (maxMatch == half) {
            char[] res = new char[n];
            for (int i = 0; i < half; i++) {
                res[i] = target.charAt(i);
                res[n - 1 - i] = target.charAt(i);
            }
            if (oddChar != -1) {
                res[half] = (char) (oddChar + 'a');
            }
            
            String p = new String(res);
            if (p.compareTo(target) > 0) return p;
        }
        
        // 4. Divergence Case: Start evaluating from the deepest possible branch point
        int start = Math.min(half - 1, maxMatch);
        int[] avail = leftCnt.clone();
        
        // Remove characters already locked in up to 'start - 1'
        for (int j = 0; j < start; j++) {
            avail[target.charAt(j) - 'a']--;
        }
        
        for (int i = start; i >= 0; i--) {
            int req = target.charAt(i) - 'a';
            
            // Look for the smallest available character strictly greater than target[i]
            for (int c = req + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    avail[c]--;
                    
                    // Optimal divergence found! Build the string instantly.
                    char[] res2 = new char[n];
                    
                    // Add identical prefix
                    for (int j = 0; j < i; j++) {
                        res2[j] = target.charAt(j);
                    }
                    
                    // Add divergent character
                    res2[i] = (char) (c + 'a');
                    int idx = i + 1;
                    
                    // Flush remaining characters in strict alphabetical order
                    for (int k = 0; k < 26; k++) {
                        while (avail[k] > 0) {
                            res2[idx++] = (char) (k + 'a');
                            avail[k]--;
                        }
                    }
                    
                    // Lock middle character for odd lengths
                    if (oddChar != -1) {
                        res2[half] = (char) (oddChar + 'a');
                    }
                    
                    // Mirror perfectly to the right half
                    for (int j = 0; j < half; j++) {
                        res2[n - 1 - j] = res2[j];
                    }
                    
                    return new String(res2);
                }
            }
            
            // If we couldn't branch at 'i', retreat to 'i - 1' and put target[i - 1] back into the pool
            if (i > 0) {
                avail[target.charAt(i - 1) - 'a']++;
            }
        }
        
        return "";
    }
}