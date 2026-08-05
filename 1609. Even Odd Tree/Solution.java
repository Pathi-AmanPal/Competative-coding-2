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
                    // Even-indexed level: values must be odd & strictly increasing
                    if (val % 2 == 0 || val <= prevVal) return false;
                } else {
                    // Odd-indexed level: values must be even & strictly decreasing
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
