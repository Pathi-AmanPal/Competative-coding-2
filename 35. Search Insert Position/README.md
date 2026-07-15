# LeetCode 35 - Search Insert Position

## 1. Intuition
The problem asks us to find the index of a target in a sorted array, or return the index where it would be inserted if it's not present. Since the array is already sorted, Binary Search is the most optimal approach. 

A key property of Binary Search is that if the target is not found, the `low` pointer will eventually cross the `high` pointer and settle at the first element that is strictly greater than the `target`. This is exactly the index where the `target` should be inserted to maintain the sorted order.

## 2. Approach
1. Initialize two pointers: `low = 0` and `high = nums.length - 1`.
2. Enter a loop while `low <= high`:
   - Calculate the middle index: `mid = low + (high - low) / 2`.
   - If `nums[mid] == target`, we have found the target, so return `mid`.
   - If the `target` is greater than `nums[mid]`, narrow the search to the right half by setting `low = mid + 1`.
   - Otherwise, narrow the search to the left half by setting `high = mid - 1`.
3. If the target is not found in the array, the loop terminates with `low` pointing to the correct insertion index. Return `low`.

### Complexity
- **Time Complexity:** $O(\log n)$ — Since we halve the search space in each step of the binary search.
- **Space Complexity:** $O(1)$ — Only a constant amount of extra memory is used for the pointers.

## 3. Code
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            else if (target > nums[mid]) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return low;
    }
}
```
