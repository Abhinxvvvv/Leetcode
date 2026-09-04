// Last updated: 04/09/2026, 11:33:07
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        
        for (String q : queries) {
            for (String d : dictionary) {
                int diff = 0;
                for (int i = 0; i < q.length(); i++) {
                    if (q.charAt(i) != d.charAt(i)) {
                        diff++;
                    }
                    // Early exit if more than 2 edits are needed
                    if (diff > 2) {
                        break;
                    }
                }
                
                // If matched with 2 or fewer edits, add it and check the next query
                if (diff <= 2) {
                    ans.add(q);
                    break; 
                }
            }
        }
        
        return ans;
    }
}
