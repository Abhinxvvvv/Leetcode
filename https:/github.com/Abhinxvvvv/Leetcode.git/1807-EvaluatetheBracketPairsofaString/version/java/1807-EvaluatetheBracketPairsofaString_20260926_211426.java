// Last updated: 26/09/2026, 21:14:26
1import java.util.HashMap;
2import java.util.List;
3import java.util.Map;
4
5class Solution {
6    public String evaluate(String s, List< List< String > > knowledge) {
7        Map< String, String > dict = new HashMap< String, String >();
8        for (List< String > pair : knowledge) {
9            dict.put(pair.get(0), pair.get(1));
10        }
11        
12        StringBuilder res = new StringBuilder();
13        char[] arr = s.toCharArray();
14        int i = 0;
15        int n = arr.length;
16        
17        while (i < n) {
18            if (arr[i] == '(') {
19                i++;
20                int start = i;
21                while (arr[i] != ')') {
22                    i++;
23                }
24                String key = new String(arr, start, i - start);
25                String val = dict.get(key);
26                if (val != null) {
27                    res.append(val);
28                } else {
29                    res.append('?');
30                }
31            } else {
32                res.append(arr[i]);
33            }
34            i++;
35        }
36        
37        return res.toString();
38    }
39}