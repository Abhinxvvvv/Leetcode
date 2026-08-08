// Last updated: 09/08/2026, 00:05:48
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int net = 0;
        int underscores = 0;
        
        // Tally up the net direction and count the blanks
        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                net--;
            } else if (c == 'R') {
                net++;
            } else {
                underscores++;
            }
        }
        
        // Absolute distance of fixed moves + all flexible moves
        return Math.abs(net) + underscores;
    }
}
