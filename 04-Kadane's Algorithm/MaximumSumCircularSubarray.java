
/*
 * Problem Name: Maximum Sum Circular Subarray
 * LeetCode Number: 918
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm
 *
 * ------------------------------------------------------------
 * APPROACH: MAXIMUM AND MINIMUM KADANE'S ALGORITHM
 * ------------------------------------------------------------
 *
 * In a normal array, a subarray must be contiguous and cannot
 * wrap around the end of the array.
 *
 * In a circular array, the last element is connected to the
 * first element. Therefore, a maximum-sum subarray can either:
 *
 * 1. Be a normal, non-circular subarray.
 * 2. Wrap around the array boundary.
 *
 * To solve this efficiently, calculate both possibilities
 * using Kadane's Algorithm.
 *
 * ------------------------------------------------------------
 * 1. NORMAL MAXIMUM SUBARRAY SUM
 * ------------------------------------------------------------
 *
 * Variables:
 * - maxsum: Maximum subarray sum ending at the current index.
 * - finalmaxsum: Maximum subarray sum found so far.
 *
 * At each element, choose between:
 * A. Extending the previous subarray.
 * B. Starting a new subarray at the current element.
 *
 * Transition:
 *
 * maxsum = max(maxsum + nums[i], nums[i])
 * finalmaxsum = max(finalmaxsum, maxsum)
 *
 * This gives the best answer when the subarray does not wrap.
 *
 * ------------------------------------------------------------
 * 2. MINIMUM SUBARRAY SUM
 * ------------------------------------------------------------
 *
 * Variables:
 * - minsum: Minimum subarray sum ending at the current index.
 * - finalminsum: Minimum subarray sum found so far.
 *
 * Transition:
 *
 * minsum = min(minsum + nums[i], nums[i])
 * finalminsum = min(finalminsum, minsum)
 *
 * Why do we need the minimum subarray?
 *
 * A circular subarray can be viewed as the total array with
 * one contiguous middle section excluded.
 *
 * To maximize the remaining sum, exclude the minimum-sum
 * subarray.
 *
 * Circular maximum sum:
 *
 * totalsum - finalminsum
 *
 * ------------------------------------------------------------
 * 3. CALCULATE THE TOTAL ARRAY SUM
 * ------------------------------------------------------------
 *
 * Variable:
 * - totalsum: Sum of all elements in the array.
 *
 * Update it for every element:
 *
 * totalsum = totalsum + nums[i]
 *
 * The maximum circular subarray sum is the larger of:
 *
 * A. finalmaxsum
 *    The best ordinary, non-circular subarray.
 *
 * B. totalsum - finalminsum
 *    The best wrapping subarray, formed by excluding the
 *    minimum-sum subarray.
 *
 * Final result:
 *
 * max(finalmaxsum, totalsum - finalminsum)
 *
 * ------------------------------------------------------------
 * 4. IMPORTANT EDGE CASE: ALL ELEMENTS ARE NEGATIVE
 * ------------------------------------------------------------
 *
 * Consider:
 *
 * nums = [-3, -2, -5]
 *
 * totalsum = -10
 * finalmaxsum = -2
 * finalminsum = -10
 *
 * If we calculate:
 *
 * totalsum - finalminsum = -10 - (-10) = 0
 *
 * This would incorrectly suggest an empty subarray, which is
 * not allowed by the problem.
 *
 * Therefore, if finalmaxsum < 0, every element is negative.
 * Return finalmaxsum directly.
 *
 * This also handles a single-element array correctly.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. A circular subarray is either a normal subarray or a
 *    wrapping subarray.
 *
 * 2. The wrapping sum can be found by subtracting the minimum
 *    subarray sum from the total array sum.
 *
 * 3. Both maximum and minimum Kadane states can be updated
 *    in the same loop.
 *
 * 4. The all-negative check is essential because subtracting
 *    the entire array as the minimum subarray would produce
 *    zero, representing an invalid empty subarray.
 *
 * 5. Initialize all states with nums[0] so that the algorithm
 *    works correctly for negative values and non-empty arrays.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * Each element is processed once.
 *
 * Space Complexity: O(1)
 * Only a fixed number of variables are maintained.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int maxsum = nums[0];
        int finalmaxsum = nums[0];

        int minsum = nums[0];
        int finalminsum = nums[0];

        int totalsum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Calculate the total sum of the array.
            totalsum = totalsum + nums[i];

            // Maximum subarray sum ending at the current index.
            maxsum = Math.max(maxsum + nums[i], nums[i]);
            finalmaxsum = Math.max(maxsum, finalmaxsum);

            // Minimum subarray sum ending at the current index.
            minsum = Math.min(minsum + nums[i], nums[i]);
            finalminsum = Math.min(finalminsum, minsum);
        }

        // If every element is negative, a wrapping calculation
        // could produce zero by excluding the entire array.
        if (finalmaxsum < 0) {
            return finalmaxsum;
        }

        // Compare the normal and circular maximum subarray sums.
        return Math.max(finalmaxsum, totalsum - finalminsum);
    }
}

