```java
/*
 * Problem: Maximum Subarray Sum with One Deletion
 * LeetCode: #1186
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm Variant (Dynamic Programming)
 *
 * ============================================================
 * APPROACH / HOW TO THINK
 * ============================================================
 *
 * We need to find the maximum sum of a non-empty contiguous
 * subarray, with the option to delete at most one element.
 *
 * Maintain two DP states:
 *
 * nodlt -> Maximum subarray sum ending at the current index
 *          without deleting any element.
 *
 * onedlt -> Maximum subarray sum ending at the current index
 *           with at most one deletion used.
 *
 * ============================================================
 * STATE TRANSITIONS
 * ============================================================
 *
 * 1. noDelete (nodlt):
 *
 * Either start a new subarray at arr[i] or extend the
 * previous subarray without deleting anything.
 *
 *     nodlt = max(arr[i], previous nodlt + arr[i])
 *
 * 2. oneDelete (onedlt):
 *
 * There are two choices:
 *
 * A. Keep the current element and extend a subarray that
 *    has already used a deletion:
 *
 *       previous onedlt + arr[i]
 *
 * B. Delete the current element and keep the best subarray
 *    ending at the previous index without deletion:
 *
 *       previous nodlt
 *
 * Therefore:
 *
 *     onedlt = max(previous onedlt + arr[i], previous nodlt)
 *
 * 3. Update the global maximum:
 *
 *     sum = max(sum, nodlt, onedlt)
 *
 * ============================================================
 * KEY INSIGHT
 * ============================================================
 *
 * Deleting the current element means the current element
 * contributes nothing to the subarray sum.
 *
 * Therefore, the deletion transition uses the PREVIOUS
 * nodlt value:
 *
 *     onedlt = previous nodlt
 *
 * Both DP states must be computed using the correct previous
 * iteration values. In this implementation, onedlt is
 * updated before nodlt, so it uses the old nodlt value.
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Initialize all states with arr[0].
 * 2. For each remaining element:
 *    a. Update onedlt using the previous onedlt and nodlt.
 *    b. Update nodlt using the previous nodlt.
 *    c. Update the global maximum.
 * 3. Return the global maximum.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Updating nodlt before calculating onedlt when the
 *    recurrence requires the previous nodlt value.
 * 2. Forgetting that deleting an element means skipping its
 *    value, not adding it to the sum.
 * 3. Allowing an empty subarray as the final answer.
 * 4. Forgetting that the deletion is optional, so the best
 *    answer may come from nodlt without any deletion.
 * 5. Updating the global maximum using only one DP state.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(n)
 * Each array element is processed once.
 *
 * Space Complexity: O(1)
 * Only a fixed number of variables are maintained.
 *
 * ============================================================
 * IMPLEMENTATION
 * ============================================================
 */

class Solution {
    public int maximumSum(int[] arr) {

        int sum = arr[0];
        int nodlt = arr[0];
        int onedlt = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Use the previous DP states before updating nodlt.
            onedlt = Math.max(onedlt + arr[i], nodlt);

            // Maximum subarray sum without deletion.
            nodlt = Math.max(nodlt + arr[i], arr[i]);

            // Update the best answer from either state.
            int localmax = Math.max(onedlt, nodlt);
            sum = Math.max(sum, localmax);
        }

        return sum;
    }
}
