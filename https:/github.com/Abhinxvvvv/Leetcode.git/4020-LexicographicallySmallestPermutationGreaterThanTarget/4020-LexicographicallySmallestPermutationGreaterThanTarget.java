// Last updated: 27/08/2026, 17:39:09
class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] sFreq = new int[26];
        
        // 1. Map occurrences of all characters in s
        for (int i = 0; i < n; i++) {
            sFreq[s.charAt(i) - 'a']++;
        }

        // 2. Find the maximum prefix length 'L' we can perfectly match with target
        int L = 0;
        int[] curFreq = new int[26];
        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            if (sFreq[c] > curFreq[c]) {
                curFreq[c]++;
                L++;
            } else {
                break;
            }
        }

        // 3. Start evaluating from the deepest possible divergence point
        int start = Math.min(L, n - 1);
        int[] avail = sFreq.clone();
        
        // Remove characters used in the matched prefix up to 'start - 1'
        for (int j = 0; j < start; j++) {
            avail[target.charAt(j) - 'a']--;
        }

        for (int i = start; i >= 0; i--) {
            int req = target.charAt(i) - 'a';
            
            // Try to find the smallest available character strictly greater than target[i]
            for (int c = req + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    avail[c]--;
                    
                    // We found our optimal divergence! Build the string instantly.
                    char[] res = new char[n];
                    
                    // Add identical prefix
                    for (int j = 0; j < i; j++) {
                        res[j] = target.charAt(j);
                    }
                    
                    // Add divergent character
                    res[i] = (char) (c + 'a');
                    int idx = i + 1;
                    
                    // Add remaining characters in strict alphabetical order
                    for (int k = 0; k < 26; k++) {
                        while (avail[k] > 0) {
                            res[idx++] = (char) (k + 'a');
                            avail[k]--;
                        }
                    }
                    return new String(res);
                }
            }
            
            // If we couldn't branch at 'i', we retreat to 'i - 1' and put the character back into the pool
            if (i > 0) {
                avail[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}