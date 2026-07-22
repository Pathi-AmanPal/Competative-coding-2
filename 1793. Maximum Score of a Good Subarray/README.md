# 1793. Maximum Score of a Good Subarray

## Problem Description

You are given an array of integers `nums` **(0-indexed)** and an integer `k`.

The **score** of a subarray `(i, j)` is defined as `min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)`. A subarray is **good** if `i <= k <= j`.

Return *the maximum possible score of a **good** subarray*.

---

### Examples

#### Example 1:
- **Input:** `nums = [1, 4, 3, 7, 4, 5]`, `k = 3`
- **Output:** `15`
- **Explanation:** The optimal subarray is `(1, 5)` with values `[4, 3, 7, 4, 5]`.
  - The minimum value is `3`.
  - The length is `5 - 1 + 1 = 5`.
  - Score = `3 * 5 = 15`.
  - Since `i = 1` and `j = 5` satisfy `1 <= k <= 5`, this is a valid good subarray.

#### Example 2:
- **Input:** `nums = [5, 5, 4, 5, 4, 1, 1, 1]`, `k = 0`
- **Output:** `20`
- **Explanation:** The optimal subarray is `(0, 4)` with values `[5, 5, 4, 5, 4]`.
  - The minimum value is `4`.
  - The length is `4 - 0 + 1 = 5`.
  - Score = `4 * 5 = 20`.

---

### Constraints
- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 2 * 10^4`
- `0 <= k < nums.length`

---

## Solution Explanation (Two Pointers / Greedy)

The problem asks us to find a subarray `[i, j]` containing index `k` that maximizes the score. The score is computed as `min(nums[i...j]) * (j - i + 1)`.

### Greedy Strategy:
1. **Initialize:** Start with the smallest possible subarray containing `k`, which is just the single element at index `k` itself (`i = k`, `j = k`).
2. **Expand:** We expand the subarray to the left or right, one step at a time.
3. **Choice of Expansion:** At each step, we look at the neighbors `nums[i - 1]` and `nums[j + 1]`. To maximize the score, we want to keep the minimum element of the subarray as large as possible. Therefore, we greedily expand towards the larger of the two adjacent elements.
4. **Update Score:** With each expansion, update the minimum value of the current subarray (`mini`) and calculate the score `mini * (j - i + 1)`. Keep track of the maximum score seen so far.
5. **Termination:** Continue this process until the subarray covers the entire array (`i = 0` and `j = n - 1`).

### Complexity:
- **Time Complexity:** $O(n)$ — We visit each index of the array at most once as we expand the window outwards.
- **Space Complexity:** $O(1)$ — Only a few integer variables are used for pointers and values.
