# LeetCode 206 - Reverse Linked List

## 1. Intuition
Reversing a singly linked list involves changing the direction of the pointers so that each node points to its preceding node instead of its successor. Since updating `current.next` breaks our reference to the rest of the list, we must temporarily store a pointer to the next node before modifying `current.next`.

## 2. Approach
1. Initialize `prev` to `null` (since the original head will become the tail pointing to `null`) and `current` to `head`.
2. Iterate through the list while `current != null`:
   - Store the next node: `ListNode nextNode = current.next`.
   - Reverse the link: `current.next = prev`.
   - Advance `prev` pointer to `current`.
   - Advance `current` pointer to `nextNode`.
3. When the loop terminates, `current` is `null` and `prev` points to the new head of the reversed list. Return `prev`.

### Complexity
- **Time Complexity:** $O(n)$ — Each node in the linked list is visited exactly once.
- **Space Complexity:** $O(1)$ — The reversal is performed in-place using only pointers.

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }
}
```
