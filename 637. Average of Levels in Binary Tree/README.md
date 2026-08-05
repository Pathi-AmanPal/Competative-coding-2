# LeetCode 637 - Average of Levels in Binary Tree

## 1. Intuition
To find the average of node values at each level, we perform level order traversal (BFS). For each level, we sum up all node values and divide by the number of nodes at that level (`levelSize`). Using a `double` variable for `sum` prevents integer overflow during accumulation.

## 2. Approach
1. Return empty list if `root == null`.
2. Queue `root` into a BFS queue.
3. For each level:
   - Record `levelSize = queue.size()`.
   - Initialize `double sum = 0`.
   - Process `levelSize` nodes:
     - Add `currentNode.val` to `sum`.
     - Enqueue left and right children if present.
   - Add `sum / levelSize` to `result`.
4. Return `result`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node is visited once.
- **Space Complexity:** $O(w)$ — Maximum width of the binary tree.

## 3. Code
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double sum = 0;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                sum += currentNode.val;
                
                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }
            result.add(sum / levelSize);
        }
        return result;
    }
}
```
