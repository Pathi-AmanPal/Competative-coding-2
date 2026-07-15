# LeetCode 33 - Search in Rotated Sorted Array

## 1. Intuition
The array is sorted but rotated at some pivot. A key property of a rotated sorted array is that when we divide it into two halves, at least one half will always be strictly sorted. We can leverage this property by using a modified Binary Search. By identifying which half is sorted and checking if our target lies within that sorted range, we can eliminate half of the search space in each step.

## 2. Approach
1. Initialize two pointers: `start = 0` and `end = nums.length - 1`.
2. Run a loop while `start <= end`:
   - Calculate `mid = start + (end - start) / 2`.
   - If `nums[mid] == target`, return `mid`.
   - Check if the left half (`nums[start]` to `nums[mid]`) is sorted:
     - If yes, check if the `target` falls within this sorted left half (`target >= nums[start]` and `target <= nums[mid]`).
       - If it does, narrow the search space to the left by setting `end = mid - 1`.
       - Otherwise, search the right half by setting `start = mid + 1`.
   - If the left half is not sorted, then the right half (`nums[mid]` to `nums[end]`) must be sorted:
     - Check if the `target` falls within this sorted right half (`target >= nums[mid]` and `target <= nums[end]`).
       - If it does, search the right half by setting `start = mid + 1`.
       - Otherwise, search the left half by setting `end = mid - 1`.
3. If the loop finishes and the target is not found, return `-1`.

### Complexity
- **Time Complexity:** $O(\log n)$ — Since we divide the search space by half in each step.
- **Space Complexity:** $O(1)$ — Only a constant amount of extra space is used for pointers.

## 3. Code
```java
class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return mid;
            }
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid - 1;
                }
                else start = mid + 1;
            }
            else if (nums[mid] <= nums[end]) {
                if (target <= nums[end] && target >= nums[mid]) {
                    start = mid + 1;
                }
                else end = mid - 1;
            }
        }
        return -1;
    }
}
```
