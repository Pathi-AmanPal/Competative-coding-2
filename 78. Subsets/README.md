# LeetCode 78 - Subsets

## 1. Intuition
To generate all possible subsets (the power set) of a set of unique elements, we can use a backtracking (depth-first search) approach. At each step in our recursive exploration, the current state of our temporary data structure (stack/list) represents a valid subset. We add a copy of this subset to our answer list and then explore further by recursively choosing subsequent elements one by one.

## 2. Approach
1. Create a result list `subsets` to store all generated subsets and a `Stack<Integer>` (or list) `stk` to keep track of the current element combination.
2. Define a recursive helper method `getSubsets(stk, startIdx, nums, subsets)`:
   - Add a snapshot (copy) of `stk` to `subsets` at the beginning of each function call.
   - Iterate through `i` from `startIdx` to `nums.length - 1`:
     - Push `nums[i]` onto `stk` to include it in the current subset.
     - Recursively call `getSubsets` with `startIdx = i + 1` to generate all combinations containing `nums[i]`.
     - Pop `nums[i]` off `stk` (backtrack) to explore other choices.
3. Call `getSubsets(stk, 0, nums, subsets)` and return `subsets`.

### Complexity
- **Time Complexity:** $O(n \cdot 2^n)$ — There are $2^n$ subsets for an array of size $n$, and copying each subset takes $O(n)$ time in the worst case.
- **Space Complexity:** $O(n)$ — The recursion call stack and auxiliary stack `stk` reach a maximum depth of $n$ (excluding the space needed for the output list).

## 3. Code
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        Stack<Integer> stk = new Stack<>();
        getSubsets(stk, 0, nums, subsets);
        
        return subsets;
    }
    
    public void getSubsets(Stack<Integer> stk, int startIdx, int[] nums, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(stk));
        
        for (int i = startIdx; i < nums.length; i++) {
            stk.push(nums[i]);            
            getSubsets(stk, i + 1, nums, subsets);
            stk.pop();
        }
        
        return;
    }
}
```
