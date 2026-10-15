```java
/*
 * Problem Name: Maximum Absolute Sum of Any Subarray
 * LeetCode Number: 1749
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm
 *
 * ------------------------------------------------------------
 * APPROACH: MAXIMUM AND MINIMUM KADANE'S ALGORITHM
 * ------------------------------------------------------------
 *
 * The objective is to find the maximum absolute sum of any
 * non-empty subarray.
 *
 * Absolute sum = |subarray sum|
 *
 * A subarray sum can be:
 * 1. Positive: its absolute value is the same positive value.
 * 2. Negative: its absolute value is the corresponding positive
 *    value.
 *
 * Therefore, the answer is the maximum of:
 * - The maximum subarray sum.
 * - The absolute value of the minimum subarray sum.
 *
 * Instead of generating every possible subarray, we use two
 * variations of Kadane's Algorithm.
 *
 * ------------------------------------------------------------
 * 1. MAXIMUM SUBARRAY SUM
 * ------------------------------------------------------------
 *
 * Variables:
 * - localpos: Maximum sum of a subarray ending at the current index.
 * - possum: Maximum subarray sum encountered so far.
 *
 * At every element, we have two choices:
 * A. Extend the previous subarray by adding the current element.
 * B. Start a new subarray from the current element.
 *
 * Transition:
 *
 * localpos = max(localpos + nums[i], nums[i])
 * possum = max(possum, localpos)
 *
 * ------------------------------------------------------------
 * 2. MINIMUM SUBARRAY SUM
 * ------------------------------------------------------------
 *
 * Variables:
 * - localneg: Minimum sum of a subarray ending at the current index.
 * - negsum: Minimum subarray sum encountered so far.
 *
 * Similarly, choose between:
 * A. Extending the previous subarray.
 * B. Starting a new subarray at the current element.
 *
 * Transition:
 *
 * localneg = min(localneg + nums[i], nums[i])
 * negsum = min(negsum, localneg)
 *
 * IMPORTANT:
 * Keep negsum negative while tracking the minimum. Do not
 * convert it to a positive value inside the loop, because that
 * would destroy the minimum-sum state needed for the next step.
 *
 * ------------------------------------------------------------
 * 3. CALCULATE THE MAXIMUM ABSOLUTE SUM
 * ------------------------------------------------------------
 *
 * The maximum absolute sum is:
 *
 * max(possum, abs(negsum))
 *
 * For example, consider:
 *
 * nums = [1, -3, 2, 3, -4]
 *
 * Maximum subarray sum = 5, from [2, 3]
 * Minimum subarray sum = -4, from [-4] or [-3, 2, 3, -4]
 *                        (the latter sums to -2)
 *
 * The maximum absolute sum is the larger of the maximum
 * positive subarray sum and the absolute minimum subarray sum.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. The maximum absolute sum can come from either a positive
 *    or a negative subarray.
 *
 * 2. Maximum Kadane alone is insufficient because a large
 *    negative sum can have a large absolute value.
 *
 * 3. Minimum Kadane finds the most negative contiguous sum.
 *
 * 4. Initialize all states with nums[0], not zero, so the
 *    algorithm correctly handles arrays containing negative
 *    values and ensures the subarray is non-empty.
 *
 * 5. Each state depends only on the previous state, so we do
 *    not need a separate DP array.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * Each array element is processed once.
 *
 * Space Complexity: O(1)
 * Only a fixed number of variables are maintained.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int possum = nums[0];
        int localpos = nums[0];

        int negsum = nums[0];
        int localneg = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Maximum subarray sum ending at the current index.
            localpos = Math.max(localpos + nums[i], nums[i]);
            possum = Math.max(localpos, possum);

            // Minimum subarray sum ending at the current index.
            localneg = Math.min(localneg + nums[i], nums[i]);
            negsum = Math.min(localneg, negsum);
        }

        // Compare the largest positive sum with the magnitude
        // of the smallest (most negative) subarray sum.
        return Math.max(possum, Math.abs(negsum));
    }
}
```
