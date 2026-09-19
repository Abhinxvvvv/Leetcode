# Last updated: 20/09/2026, 00:34:48
1class Solution:
2    def checkOverlap(self, radius: int, xCenter: int, yCenter: int, x1: int, y1: int, x2: int, y2: int) -> bool:
3        closest_x = max(x1, min(xCenter, x2))
4        closest_y = max(y1, min(yCenter, y2))
5        
6        distance_x = xCenter - closest_x
7        distance_y = yCenter - closest_y
8        
9        distance_squared = (distance_x ** 2) + (distance_y ** 2)
10        
11        return distance_squared <= (radius ** 2)