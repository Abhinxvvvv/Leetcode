// Last updated: 09/08/2026, 00:05:40
class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        // Tracks the index of the best matching container word at this suffix level
        int bestIndex = -1; 
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        
        // 1. Find the absolute default best index (global shortest, then earliest)
        int defaultBestIdx = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[defaultBestIdx].length()) {
                defaultBestIdx = i;
            }
        }
        root.bestIndex = defaultBestIdx;

        // 2. Build the Suffix Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            int len = word.length();
            TrieNode curr = root;

            // Insert characters from right to left
            for (int j = len - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];

                // Update node's best index based on shortest-length greedy rules
                if (curr.bestIndex == -1) {
                    curr.bestIndex = i;
                } else {
                    int existingLen = wordsContainer[curr.bestIndex].length();
                    if (len < existingLen) {
                        curr.bestIndex = i;
                    }
                    // Since we iterate 'i' ascending, earlier indices are naturally 
                    // preserved if lengths match exactly (len == existingLen).
                }
            }
        }

        // 3. Process Queries Linearly
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            int len = query.length();
            TrieNode curr = root;
            int lastValidBest = root.bestIndex;

            // Travel down the suffix trail backwards
            for (int j = len - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    break; // Suffix match broke, keep the deepest found answer
                }
                curr = curr.children[charIdx];
                lastValidBest = curr.bestIndex;
            }
            ans[i] = lastValidBest;
        }

        return ans;
    }
}
