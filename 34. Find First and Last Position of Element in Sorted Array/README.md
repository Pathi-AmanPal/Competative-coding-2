# LeetCode 34 - Find First and Last Position of Element in Sorted Array

## 1. Intuition
Since the array is sorted, we can search for the target using Binary Search in $O(\log n)$ time. To find the first and last occurrences, we can run two separate binary searches:
- One to find the **first occurrence**: When we find the target, we record its index and continue searching in the left half.
- One to find the **last occurrence**: When we find the target, we record its index and continue searching in the right half.

## 2. Approach
1. **First Occurrence (`firstOcc`)**:
   - Initialize `left = 0` and `right = nums.length - 1`.
   - While `left <= right`:
     - Calculate `mid = left + (right - left) / 2`.
     - If `nums[mid] == target`, store `index = mid` as a potential candidate, and move `right = mid - 1` to look for a smaller index.
     - If `target <= nums[mid]`, search the left half (`right = mid - 1`).
     - Otherwise, search the right half (`left = mid + 1`).
2. **Last Occurrence (`lastOcc`)**:
   - Initialize `left = 0` and `right = nums.length - 1`.
   - While `left <= right`:
     - Calculate `mid = left + (right - left) / 2`.
     - If `nums[mid] == target`, store `index = mid` as a potential candidate, and move `left = mid + 1` to look for a larger index.
     - If `target >= nums[mid]`, search the right half (`left = mid + 1`).
     - Otherwise, search the left half (`right = mid - 1`).
3. Combine the results from both helper methods into an array `[firstOcc, lastOcc]` and return it.

### Complexity
- **Time Complexity:** $O(\log n)$ — We perform two independent binary searches, each taking $O(\log n)$ time.
- **Space Complexity:** $O(1)$ — Only a constant amount of extra memory is used.

## 3. Code
```java
class Solution {
    public static int firstOcc(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                index = mid;
            }
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static int lastOcc(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                index = mid;
            }
            if (target >= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    public int[] searchRange(int[] nums, int target) {
        int arr[] = new int[2];
        arr[0] = firstOcc(nums, target);
        arr[1] = lastOcc(nums, target);
        return arr;
    }
}
```
