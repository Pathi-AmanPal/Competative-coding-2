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
