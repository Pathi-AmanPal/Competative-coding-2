# LeetCode 958 - Check Completeness of a Binary Tree

## 1. Intuition
In a complete binary tree, every level except possibly the last is completely filled, and all nodes in the last level are as far left as possible. This means during a level order traversal (BFS), once we encounter a `null` node, we should **never** encounter another non-null node afterwards.

## 2. Approach
1. Return `true` if `root == null`.
2. Initialize a BFS `Queue<TreeNode>` and push `root`. Maintain a boolean `nullSeen = false`.
3. Loop while the queue is not empty:
   - Poll `currentNode` from the queue.
   - If `currentNode == null`:
     - Set `nullSeen = true`.
   - Else:
     - If `nullSeen` is `true`, a gap was found earlier in the tree level order sequence, so return `false`.
     - Offer `currentNode.left` to the queue (even if `null`).
     - Offer `currentNode.right` to the queue (even if `null`).
4. If the loop finishes without finding invalid non-null nodes after a `null`, return `true`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node (including `null` markers) is pushed and popped at most once.
- **Space Complexity:** $O(w)$ — Maximum width of the tree stored in the queue.

## 3. Code
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean nullSeen = false;
        
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            
            if (currentNode == null) {
                nullSeen = true;
            } else {
                if (nullSeen) return false;
                queue.offer(currentNode.left);
                queue.offer(currentNode.right);
            }
        }
        return true;
    }
}
```
