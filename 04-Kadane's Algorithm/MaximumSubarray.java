```java
/*
 * Problem: Maximum Subarray
 * LeetCode: #53
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm (Dynamic Programming)
 *
 * ============================================================
 * APPROACH
 * ============================================================
 *
 * Use Kadane's Algorithm to find the contiguous subarray
 * with the largest sum.
 *
 * Maintain two variables:
 *
 * currSum -> Maximum subarray sum ending at the current index.
 * maxSum  -> Maximum subarray sum found so far.
 *
 * At every index, there are two choices:
 *
 * 1. Start a new subarray from nums[i].
 * 2. Extend the previous subarray by adding nums[i].
 *
 * Transition:
 *
 *     currSum = Math.max(nums[i], currSum + nums[i])
 *
 * Update the overall maximum:
 *
 *     maxSum = Math.max(maxSum, currSum)
 *
 * ============================================================
 * WHY DOES THIS WORK?
 * ============================================================
 *
 * currSum answers:
 * "What is the best subarray sum ending at this index?"
 *
 * maxSum answers:
 * "What is the best subarray sum found anywhere so far?"
 *
 * If currSum + nums[i] is smaller than nums[i], the previous
 * subarray is no longer beneficial. Start a new subarray
 * at the current element instead.
 *
 * Otherwise, extend the existing subarray.
 *
 * ============================================================
 * EXAMPLE
 * ============================================================
 *
 * Input:
 * nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *
 * Initial values:
 * currSum = -2
 * maxSum  = -2
 *
 * At each index, choose whether to start a new subarray
 * or extend the previous one.
 *
 * The maximum-sum subarray is:
 *
 *     [4, -1, 2, 1]
 *
 * Its sum is:
 *
 *     4 + (-1) + 2 + 1 = 6
 *
 * Output: 6
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Initialize:
 *
 *        currSum = nums[0]
 *        maxSum  = nums[0]
 *
 * 2. For every remaining element:
 *
 *        currSum = Math.max(nums[i], currSum + nums[i])
 *        maxSum  = Math.max(maxSum, currSum)
 *
 * 3. Return maxSum.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Using maxSum to build the next sum instead of currSum.
 * 2. Mixing the current subarray sum with the global maximum.
 * 3. Initializing both variables to 0, which breaks cases
 *    where every array element is negative.
 * 4. Forgetting to update maxSum after updating currSum.
 * 5. Assuming the subarray must include every array element.
 *    The subarray must be contiguous, but it can start and
 *    end at any valid positions.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(n)
 * Each element is processed once.
 *
 * Space Complexity: O(1)
 * Only two variables are maintained, excluding the input.
 *
 * ============================================================
 * IMPLEMENTATION
 * ============================================================
 */

class Solution {
    public int maxSubArray(int[] nums) {

        int currSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currSum = Math.max(nums[i], currSum + nums[i]);

            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}
```