# LeetCode 84 - Largest Rectangle in Histogram

## 1. Intuition
To find the largest rectangle in a histogram, we need to consider each bar as the minimum height of a rectangle and determine how far left and right that rectangle can extend. The rectangle can extend as long as adjacent bars are of height greater than or equal to the current bar. Using a monotonic increasing stack allows us to efficiently find the Next Smaller Element (NSE) on the right and Previous Smaller Element (PSE) on the left for each bar in a single pass.

## 2. Approach
1. Initialize an empty `Stack<Integer>` to store indices of bars in strictly increasing height order.
2. Initialize `maxArea = 0`.
3. Loop `i` from `0` to `n` (inclusive, where `n = heights.length`):
   - For `i == n`, set `currentHeight = 0` to flush out all remaining bars in the stack.
   - While stack is not empty and `heights[stack.peek()] > currentHeight`:
     - Pop the top index `hIdx` from the stack. The height of the rectangle is `height = heights[hIdx]`.
     - The right boundary is `i` (index of Next Smaller Element).
     - The left boundary is `stack.peek()` (index of Previous Smaller Element). If stack is empty, it means this bar is smaller than all preceding elements, so left boundary is `-1`.
     - Width of rectangle: `width = stack.isEmpty() ? i : i - stack.peek() - 1`.
     - Calculate area: `height * width` and update `maxArea = Math.max(maxArea, area)`.
   - Push `i` onto the stack.
4. Return `maxArea`.

### Complexity
- **Time Complexity:** $O(n)$ — Each index is pushed onto and popped from the stack at most once.
- **Space Complexity:** $O(n)$ — Stack space used to store bar indices.

## 3. Code
```java
import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }

        return maxArea;
    }
}
```
