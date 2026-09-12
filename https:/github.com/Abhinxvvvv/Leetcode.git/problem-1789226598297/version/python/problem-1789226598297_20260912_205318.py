# Last updated: 12/09/2026, 20:53:18
1from bisect import bisect_left, bisect_right
2class Solution(object):
3    def distantSubarrays(self, nums, goal, k):
4        P=[0]
5        for x in nums:
6            P.append(P[-1]+x)
7
8        vals=sorted(set(P))
9        m= len(vals)
10        bit = [0]*(m+1)
11        ans = 0
12
13        def update(i):
14            while i<=m:
15                bit[i] +=1
16                i += i & -i
17
18        def query(i):
19            s=0
20            while i>0:
21                s+=bit[i]
22                i -= i & -i
23            return s
24
25        for j, p in enumerate(P):
26            if j>0:
27                if k==0:
28                    ans += j
29                else:
30                    c1 = query(bisect_right(vals,p - goal -k))
31                    c2 = query(m) - query(bisect_left(vals, p - goal +k))
32                    ans+= c1 + c2
33            update(bisect_right(vals, p))
34
35        return ans
36        