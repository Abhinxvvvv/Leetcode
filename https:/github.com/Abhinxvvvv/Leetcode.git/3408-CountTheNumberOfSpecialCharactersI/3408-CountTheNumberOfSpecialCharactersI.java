// Last updated: 09/08/2026, 00:05:34
class Solution {
    public int numberOfSpecialChars(String word) {
        int lowerMask = 0;
        int upperMask = 0;
        int len = word.length();
        
        // Single pass extracting characters directly from the string layout
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lowerMask |= (1 << (c - 'a'));
            } else {
                upperMask |= (1 << (c - 'A'));
            }
        }
        
        // Bitwise AND targets only indices present in both masks
        return Integer.bitCount(lowerMask & upperMask);
    }
}
