// Last updated: 09/08/2026, 00:07:28
#include <string>

class Solution {
public:
    std::string longestPalindrome(std::string s) {
        if (s.length() < 2) {
            return s;
        }

        int n = s.length();
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < n; ) {
            // OPTIMIZATION 1: Early Exit
            // If the remaining characters cannot possibly form a longer palindrome, stop.
            if (n - i <= maxLen / 2) {
                break;
            }

            int left = i;
            int right = i;

            // OPTIMIZATION 2: Skip Duplicates
            // Instantly bypasses blocks of identical characters (like "bbbbb")
            while (right < n - 1 && s[right + 1] == s[right]) {
                right++;
            }
            
            // Move the main loop pointer past the identical block
            i = right + 1;

            // OPTIMIZATION 3: Expand Around Center
            while (left > 0 && right < n - 1 && s[left - 1] == s[right + 1]) {
                left--;
                right++;
            }

            // Update max length and starting index
            int currLen = right - left + 1;
            if (currLen > maxLen) {
                maxStart = left;
                maxLen = currLen;
            }
        }

        // Only allocate memory for the final substring once
        return s.substr(maxStart, maxLen);
    }
};