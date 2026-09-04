// Last updated: 04/09/2026, 11:34:22
class Solution {
    public boolean canReach(int[] arr, int start) {
        // Out of bounds check or hitting a cycle (negative values)
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        
        // Base Case: Target found!
        if (arr[start] == 0) {
            return true;
        }
        
        // Save the value before marking it as visited
        int jumpLength = arr[start];
        
        // In-place marker: Flip the value to negative to mark it visited
        arr[start] = -jumpLength;
        
        // Recursively check forward jump and backward jump
        return canReach(arr, start + jumpLength) || canReach(arr, start - jumpLength);
    }
}