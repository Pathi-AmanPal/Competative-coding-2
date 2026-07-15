# LeetCode 238 - Product of Array Except Self

## 1. Intuition
If we were allowed to use division, this problem would be trivial: we could just multiply all the numbers together to get a total product, and then for each element, divide that total by the element itself. But since division is forbidden, we have to think about what the product "except self" actually is.

For any element at index `i`, the result is the product of all numbers to its left multiplied by the product of all numbers to its right. If we can precompute these left and right products, we can get our answers. Even better, we can do this in-place using our output array so we don't waste any extra memory.

## 2. Approach
We can solve this in two simple passes:
1. **Left Products Pass**: We initialize our output array `ans` and set `ans[0] = 1` (since there's nothing to the left of the first number). Then, we loop forward. At each index `i`, `ans[i]` will store the product of all numbers to the left of `i`, which is just `ans[i - 1] * nums[i - 1]`.
2. **Right Products Pass**: We loop backward from the end of the array. We keep a running variable `right` (initialized to `1`) to store the product of everything to the right of our current position. At each index `i`, we multiply our existing left product `ans[i]` by `right`. Then, we update `right` by multiplying it with `nums[i]` so it's ready for the next step.

### Complexity
- **Time Complexity:** $O(n)$ — We traverse the array exactly twice.
- **Space Complexity:** $O(1)$ — We don't count the output array as extra space, and we only use a single integer variable (`right`) to keep track of the running right product.

## 3. Code
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right *= nums[i];
        }

        return ans;
    }
}
```
