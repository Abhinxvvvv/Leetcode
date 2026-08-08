# Last updated: 09/08/2026, 00:05:02
class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        return all(abs(int(a) - int(b)) <= 2 for a, b in zip(s,s[1:]))
        