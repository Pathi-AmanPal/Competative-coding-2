# LeetCode 515 - Find Largest Value in Each Tree Row

## 1. Intuition
To find the maximum value in each row of a binary tree, we can perform a level order traversal (BFS). For each row (level), we keep track of the maximum value encountered among all nodes at that level and append it to our result list.

## 2. Approach
1. Return empty list if `root == null`.
2. Initialize a queue for BFS and enqueue `root`.
3. For each level:
   - Determine `levelSize = queue.size()`.
   - Set `maxVal = Integer.MIN_VALUE`.
   - Process `levelSize` nodes:
     - Poll `currentNode` from the queue.
     - Update `maxVal = Math.max(maxVal, currentNode.val)`.
     - Enqueue left and right children if non-null.
   - Add `maxVal` to `result`.
4. Return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node is polled and processed once.
- **Space Complexity:** $O(w)$ — Maximum width of the binary tree.

## 3. Code
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxVal = Integer.MIN_VALUE;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                maxVal = Math.max(maxVal, currentNode.val);
                
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            result.add(maxVal);
        }
        return result;
    }
}
```
