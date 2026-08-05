# LeetCode 102 - Binary Tree Level Order Traversal

## 1. Intuition
Level order traversal requires visiting all nodes level by level from left to right. A Queue data structure fits naturally here because of its First-In-First-Out (FIFO) ordering. By keeping track of the number of nodes at each level (`queue.size()`), we can process all nodes belonging to the current level in a single batch before advancing to the next level.

## 2. Approach
1. Handle edge case: if `root` is `null`, return an empty list.
2. Initialize a queue and offer the `root` node.
3. While the queue is not empty:
   - Determine the number of nodes at the current level (`levelSize = queue.size()`).
   - Create a list `currentLevel` to store the node values of this level.
   - Iterate `levelSize` times:
     - Poll a node from the queue and add its value to `currentLevel`.
     - Enqueue left child if present.
     - Enqueue right child if present.
   - Add `currentLevel` to the final `result` list.
4. Return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node in the binary tree is processed exactly once.
- **Space Complexity:** $O(w)$ — Maximum width of the tree, which can be up to $O(n)$ for a balanced complete tree (holds up to $n/2$ nodes at the lowest level).

## 3. Code
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
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
            result.add(currentLevel);
        }
        return result;
    }
}
```
