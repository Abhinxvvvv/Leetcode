// Last updated: 24/08/2026, 16:30:28
class Solution {
    public boolean sumGame(String num) {
        char[] chars = num.toCharArray();
        int mid = chars.length / 2;
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < mid; i++) {
            if (chars[i] == '?') {
                qDiff++;
            } else {
                sumDiff += chars[i] - '0';
            }
            
            if (chars[i + mid] == '?') {
                qDiff--;
            } else {
                sumDiff -= chars[i + mid] - '0';
            }
        }

        if (qDiff % 2 != 0) {
            return true;
        }

        return sumDiff * 2 != -qDiff * 9;
    }
}