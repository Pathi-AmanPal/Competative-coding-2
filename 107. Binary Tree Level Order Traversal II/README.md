# LeetCode 107 - Binary Tree Level Order Traversal II

## 1. Intuition
This problem requires returning the level order traversal from bottom to top (leaf level to root level). We can perform a standard top-down BFS using a queue, but insert each newly constructed level list at index `0` of our result list (`LinkedList`), effectively building the bottom-up result order dynamically.

## 2. Approach
1. Return empty list if `root == null`.
2. Use a `LinkedList<List<Integer>>` for `result` to allow efficient $O(1)$ insertion at index `0`.
3. Use a `Queue<TreeNode>` for standard BFS traversal.
4. For each level:
   - Collect values into `currentLevel`.
   - Add `currentLevel` to the front of `result` (`result.add(0, currentLevel)`).
5. Return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node is visited once and prepended to the result list.
- **Space Complexity:** $O(w)$ — Auxiliary space for the BFS queue.

## 3. Code
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            result.add(0, currentLevel);
        }
        return result;
    }
}
```
