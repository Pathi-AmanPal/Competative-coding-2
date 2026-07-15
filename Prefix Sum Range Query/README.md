# Prefix Sum Range Query

## 1. Intuition
When we need to find the sum of elements in a specific range of an array, the most basic way is to run a loop from the starting index `L` to the ending index `R` and add them up. But if we have to do this many times (multiple queries), looping every time is going to be really slow, especially if the array is large.

To speed this up, we can calculate all the running sums beforehand. If we know the sum from the beginning up to every index, we can find the sum of any sub-segment in just one subtraction. That's the core idea of a prefix sum array.

## 2. Approach
First, we build the prefix sum array. The first element is the same as the original array's first element. For every other index, we just add the current element to the sum we calculated for the previous element.

Once we have this prefix sum array:
- If we want the sum from index `L` to `R`:
  - If `L` is `0`, the sum is simply the prefix sum at `R`.
  - If `L` is greater than `0`, we take the prefix sum at `R` and subtract the prefix sum at `L - 1`. This cuts off the starting part of the array that we don't want in our range sum.

### Complexity
- **Time Complexity:** Preprocessing takes $O(n)$ because we iterate through the array once. Answering each query takes $O(1)$ since it's just a simple subtraction.
- **Space Complexity:** $O(n)$ because we need to store the prefix sum array.

## 3. Code
```java
class Solution {
    public static int[] prefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        return prefix;
    }

    public static int rangeSum(int[] prefix, int L, int R) {
        if (L == 0) {
            return prefix[R];
        }
        return prefix[R] - prefix[L - 1];
    }
}
```
