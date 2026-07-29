# LeetCode 328 - Odd Even Linked List

## 1. Intuition
The problem asks us to rearrange a singly-linked list such that all nodes at odd indices appear first, followed by all nodes at even indices (based on node positions, not their values). To perform this rearrangement in $O(1)$ extra space and $O(n)$ time complexity, we can split the list into two separate sublists (one for odd-positioned nodes and one for even-positioned nodes) during a single traversal, and then stitch the tail of the odd sublist to the head of the even sublist.

## 2. Approach
1. **Edge Case**: If `head` is `null` or contains only one node (`head.next == null`), return `head` directly since no reordering is needed.
2. Initialize pointers:
   - `odd`: Points to the current odd node, starting at `head`.
   - `even`: Points to the current even node, starting at `head.next`.
   - `evenHead`: Saves the reference to `even` so we can attach it to the end of the odd sublist later.
3. Loop while `even != null` and `even.next != null`:
   - Connect `odd.next` to `even.next` (the next odd node) and advance `odd` pointer.
   - Connect `even.next` to `odd.next` (the next even node) and advance `even` pointer.
4. After the loop, link `odd.next = evenHead` to concatenate the odd and even lists.
5. Return `head`.

### Complexity
- **Time Complexity:** $O(n)$ — We iterate through all $n$ nodes in a single linear pass.
- **Space Complexity:** $O(1)$ — Rearrangement is done in-place using only pointers.

## 3. Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```
