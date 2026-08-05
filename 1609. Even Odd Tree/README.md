# LeetCode 1609 - Even Odd Tree

## 1. Intuition
An Even-Odd Tree follows strict rules depending on whether the level index is even or odd:
- **Even level (0, 2, 4, ...)**: Node values must be **odd** integers and strictly **increasing** from left to right.
- **Odd level (1, 3, 5, ...)**: Node values must be **even** integers and strictly **decreasing** from left to right.

We can perform a level order traversal (BFS) while keeping track of `prevVal` initialized appropriately for each level (`Integer.MIN_VALUE` for even levels and `Integer.MAX_VALUE` for odd levels).

## 2. Approach
1. Return `true` if `root == null`.
2. Initialize BFS queue and offer `root`. Maintain `level = 0`.
3. For each level:
   - Record `levelSize = queue.size()`.
   - Initialize `prevVal`: `Integer.MIN_VALUE` if `level % 2 == 0` else `Integer.MAX_VALUE`.
   - Iterate `levelSize` times:
     - Poll `currentNode`.
     - Check constraints:
       - If `level % 2 == 0`: return `false` if `val` is even (`val % 2 == 0`) or `val <= prevVal`.
       - If `level % 2 != 0`: return `false` if `val` is odd (`val % 2 != 0`) or `val >= prevVal`.
     - Update `prevVal = val`.
     - Enqueue left and right non-null children.
   - Increment `level++`.
4. If traversal completes without constraint violations, return `true`.

### Complexity
- **Time Complexity:** $O(n)$ — Visits each node once.
- **Space Complexity:** $O(w)$ — Maximum width of the binary tree.

## 3. Code
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int prevVal = (level % 2 == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                int val = currentNode.val;
                
                if (level % 2 == 0) {
                    if (val % 2 == 0 || val <= prevVal) return false;
                } else {
                    if (val % 2 != 0 || val >= prevVal) return false;
                }
                
                prevVal = val;
                
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            level++;
        }
        return true;
    }
}
```
