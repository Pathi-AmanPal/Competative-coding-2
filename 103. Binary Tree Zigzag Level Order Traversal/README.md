# LeetCode 103 - Binary Tree Zigzag Level Order Traversal

## 1. Intuition
Zigzag level order traversal is a variation of standard level order traversal where nodes are visited left-to-right at even levels and right-to-left at odd levels. Instead of performing a standard level order traversal and reversing alternate level lists afterwards, we can use a double-ended queue (`LinkedList`) for each level and insert elements at either the tail or the head depending on the current direction flag.

## 2. Approach
1. Return empty list if `root == null`.
2. Initialize a BFS `Queue<TreeNode>` and push `root`. Maintain a boolean flag `leftToRight = true`.
3. For each level:
   - Determine `levelSize = queue.size()`.
   - Initialize a `LinkedList<Integer> currentLevel`.
   - Process `levelSize` nodes:
     - If `leftToRight` is `true`, append `node.val` to the end (`addLast`).
     - If `leftToRight` is `false`, prepend `node.val` to the front (`addFirst`).
     - Enqueue left and right children as usual.
   - Add `currentLevel` to `result` and toggle `leftToRight = !leftToRight`.
4. Return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node is visited and inserted into the level deque in $O(1)$ time.
- **Space Complexity:** $O(w)$ — Maximum width of the tree stored in the queue.

## 3. Code
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                if (leftToRight) {
                    currentLevel.addLast(currentNode.val);
                } else {
                    currentLevel.addFirst(currentNode.val);
                }
                
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return result;
    }
}
```
