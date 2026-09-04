# Last updated: 04/09/2026, 11:31:09
class Solution:
    def isAdjacentDiffAtMostTwo(self, s: str) -> bool:
        return all(abs(int(a) - int(b)) <= 2 for a, b in zip(s,s[1:]))
        