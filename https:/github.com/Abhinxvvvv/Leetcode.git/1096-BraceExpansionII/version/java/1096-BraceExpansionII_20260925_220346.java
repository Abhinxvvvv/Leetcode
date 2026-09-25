// Last updated: 25/09/2026, 22:03:46
1import java.util.ArrayList;
2import java.util.Collections;
3import java.util.HashSet;
4import java.util.List;
5import java.util.Set;
6
7class Solution {
8    public List braceExpansionII(String expression) {
9        int[] pos = new int[1];
10        Set set = new HashSet(parse(expression.toCharArray(), pos));
11        List ans = new ArrayList(set);
12        Collections.sort(ans);
13        return ans;
14    }
15
16    private List parse(char[] exp, int[] pos) {
17        List res = new ArrayList();
18        List cur = new ArrayList();
19        cur.add("");
20
21        while (pos[0] < exp.length && exp[pos[0]] != '}') {
22            if (exp[pos[0]] == ',') {
23                for (int i = 0; i < cur.size(); i++) {
24                    res.add((String) cur.get(i));
25                }
26                cur = new ArrayList();
27                cur.add("");
28                pos[0]++;
29            } else if (exp[pos[0]] == '{') {
30                pos[0]++;
31                List next = parse(exp, pos);
32                pos[0]++;
33                
34                List temp = new ArrayList();
35                for (int i = 0; i < cur.size(); i++) {
36                    String s1 = (String) cur.get(i);
37                    for (int j = 0; j < next.size(); j++) {
38                        String s2 = (String) next.get(j);
39                        temp.add(s1 + s2);
40                    }
41                }
42                cur = temp;
43            } else {
44                int start = pos[0];
45                while (pos[0] < exp.length && exp[pos[0]] >= 'a' && exp[pos[0]] <= 'z') {
46                    pos[0]++;
47                }
48                String str = new String(exp, start, pos[0] - start);
49                
50                List temp = new ArrayList();
51                for (int i = 0; i < cur.size(); i++) {
52                    String s1 = (String) cur.get(i);
53                    temp.add(s1 + str);
54                }
55                cur = temp;
56            }
57        }
58        for (int i = 0; i < cur.size(); i++) {
59            res.add((String) cur.get(i));
60        }
61        return res;
62    }
63}