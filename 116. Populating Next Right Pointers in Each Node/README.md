# LeetCode 116 - Populating Next Right Pointers in Each Node

## 1. Intuition
The tree is guaranteed to be a **perfect binary tree** (all leaves are on the same level and every parent has two children). While a standard BFS queue can solve this in $O(n)$ space, we can achieve $O(1)$ extra space by utilizing the `next` pointers that have already been established on the current level to connect the children on the next level.

## 2. Approach
1. Return `null` if `root == null`.
2. Start at `leftmost = root`.
3. Loop while `leftmost.left != null` (since it is a perfect binary tree, we stop when we reach the leaf level):
   - Traverse the current level horizontally starting from `curr = leftmost`.
   - While `curr != null`:
     - Connect left child to right child: `curr.left.next = curr.right`.
     - Connect right child to adjacent left child across parents: if `curr.next != null`, `curr.right.next = curr.next.left`.
     - Move to next node on current level: `curr = curr.next`.
   - Advance down to next level: `leftmost = leftmost.left`.
4. Return `root`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node and pointer connection is visited once.
- **Space Complexity:** $O(1)$ — Uses constant extra space (no queue or recursion stack).

## 3. Code
```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        
        Node leftmost = root;
        while (leftmost.left != null) {
            Node curr = leftmost;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }
}
```
