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
