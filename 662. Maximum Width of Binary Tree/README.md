# LeetCode 662 - Maximum Width of Binary Tree

## 1. Intuition
The width of a level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes in the level), including any `null` nodes between them. If we index nodes in a binary tree like a heap (root at `0`, left child at `2 * i + 1`, right child at `2 * i + 2`), the width of any level is simply `last_index - first_index + 1`. To prevent integer overflow for deep trees, we normalize indices at each level by subtracting the level's minimum index (`minIndex`).

## 2. Approach
1. Return `0` if `root == null`.
2. Maintain a `Queue<Pair>` where `Pair` stores `(TreeNode node, int index)`.
3. Offer `(root, 0)` to the queue.
4. For each level:
   - Record `levelSize = queue.size()`.
   - Store `minIndex = queue.peek().index`.
   - Track `first` (index of first node in level) and `last` (index of last node in level).
   - For each node:
     - Compute normalized index: `curIndex = current.index - minIndex`.
     - If `i == 0`, `first = curIndex`.
     - If `i == levelSize - 1`, `last = curIndex`.
     - Enqueue left child with index `2 * curIndex + 1`.
     - Enqueue right child with index `2 * curIndex + 2`.
   - Update `maxWidth = Math.max(maxWidth, last - first + 1)`.
5. Return `maxWidth`.

### Complexity
- **Time Complexity:** $O(n)$ — Visits every node once.
- **Space Complexity:** $O(w)$ — Max queue size proportional to maximum tree width.

## 3. Code
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Pair {
        TreeNode node;
        int index;
        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int minIndex = queue.peek().index;
            int first = 0, last = 0;
            
            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                TreeNode node = current.node;
                int curIndex = current.index - minIndex;
                
                if (i == 0) first = curIndex;
                if (i == levelSize - 1) last = curIndex;
                
                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * curIndex + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * curIndex + 2));
                }
            }
            maxWidth = Math.max(maxWidth, last - first + 1);
        }
        return maxWidth;
    }
}
```
