# LeetCode 219 - Contains Duplicate II

## 1. Intuition
The goal is to find two identical numbers in the array that are close to each other—specifically, at most `k` indices apart.

A brute-force solution would compare every element with all other elements within a distance of `k`, but that would be too slow. Instead, we can think of this as a sliding window of size `k`. We only need to remember the numbers we have seen within this window. A hash set is perfect for this because it lets us check if a number has been seen before in constant time.

## 2. Approach
We use a sliding window implemented with a `HashSet`:
1. Loop through the array from start to finish.
2. If our current index `i` is greater than `k`, the element at `i - k - 1` is now too far away to be a valid duplicate partner. We remove it from our hash set to keep the window size bounded by `k`.
3. We then check if the current element `nums[i]` is already in our set.
   - If it is, we found a duplicate within distance `k`, so we return `true` immediately.
   - If it isn't, we add it to our set and move to the next index.
4. If we finish the loop without finding any duplicates within distance `k`, we return `false`.

### Complexity
- **Time Complexity:** $O(n)$ — We do a single pass over the array, and each hash set operation (add, remove, contains) takes $O(1)$ average time.
- **Space Complexity:** $O(\min(n, k))$ — The hash set stores at most $k$ elements at any given time.

## 3. Code
```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
```
