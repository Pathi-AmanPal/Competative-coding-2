# LeetCode 117 - Populating Next Right Pointers in Each Node II

## 1. Intuition
Unlike LeetCode 116, the binary tree here is not necessarily perfect and nodes may have missing children. To connect `next` pointers in $O(1)$ extra space, we can construct a linked list of nodes for the next level using a `dummy` head node and a `prev` pointer while iterating across the `next` pointers of the current level.

## 2. Approach
1. Return `null` if `root == null`.
2. Start `curr = root`.
3. While `curr != null`:
   - Initialize a `dummy` node (`Node dummy = new Node(0)`) and set `prev = dummy`.
   - Traverse the current level using `curr`:
     - If `curr.left != null`, append it (`prev.next = curr.left`) and update `prev = prev.next`.
     - If `curr.right != null`, append it (`prev.next = curr.right`) and update `prev = prev.next`.
     - Move `curr = curr.next`.
   - Jump to the head of the next level: `curr = dummy.next`.
4. Return `root`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node is visited once.
- **Space Complexity:** $O(1)$ — Uses constant extra space (pointers and dummy nodes).

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
        
        Node curr = root;
        while (curr != null) {
            Node dummy = new Node(0);
            Node prev = dummy;
            
            while (curr != null) {
                if (curr.left != null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if (curr.right != null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                curr = curr.next;
            }
            curr = dummy.next;
        }
        return root;
    }
}
```
