# LeetCode 199 - Binary Tree Right Side View

## 1. Intuition
The right side view of a binary tree contains the rightmost node at each level. If we traverse the tree using DFS visiting the right child before the left child, the first node encountered at any level will always be the rightmost node of that level.

## 2. Approach
1. Initialize an empty list `result`.
2. Define a recursive DFS function `dfs(node, depth, result)`:
   - Base Case: If `node == null`, return.
   - If `depth == result.size()`, it means we are visiting this depth for the first time. Because right subtrees are visited first, `node.val` is the rightmost node at this depth, so we add `node.val` to `result`.
   - Recursively call `dfs(node.right, depth + 1, result)`.
   - Recursively call `dfs(node.left, depth + 1, result)`.
3. Call `dfs(root, 0, result)` and return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Visits each node at most once.
- **Space Complexity:** $O(h)$ — Call stack space proportional to the height $h$ of the tree ($O(\log n)$ for balanced trees, $O(n)$ for skewed trees).

## 3. Code
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) return;
        
        if (depth == result.size()) {
            result.add(node.val);
        }
        
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }
}
```
