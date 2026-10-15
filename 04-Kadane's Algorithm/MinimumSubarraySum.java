```java
/*
 * Problem: Minimum Subarray Sum
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm (Minimum Subarray)
 *
 * ============================================================
 * APPROACH
 * ============================================================
 *
 * Use Kadane's Algorithm to find the contiguous subarray
 * with the minimum possible sum.
 *
 * Maintain two variables:
 *
 * currsum -> Minimum subarray sum ending at the current index.
 * minsum  -> Minimum subarray sum found so far.
 *
 * At every index, there are two choices:
 *
 * 1. Start a new subarray from arr[i].
 * 2. Extend the previous subarray by adding arr[i].
 *
 * Recurrence:
 *
 *     currsum = Math.min(arr[i], currsum + arr[i])
 *
 * Update the overall minimum:
 *
 *     minsum = Math.min(minsum, currsum)
 *
 * ============================================================
 * KEY OBSERVATION
 * ============================================================
 *
 * currsum answers:
 * "What is the minimum subarray sum ending at this index?"
 *
 * minsum answers:
 * "What is the minimum subarray sum found anywhere so far?"
 *
 * If extending the previous subarray produces a larger sum
 * than starting at arr[i], discard the previous subarray
 * and start a new one at the current element.
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Initialize:
 *
 *        currsum = arr[0]
 *        minsum  = arr[0]
 *
 * 2. For every remaining element:
 *
 *        currsum = Math.min(arr[i], currsum + arr[i])
 *        minsum  = Math.min(minsum, currsum)
 *
 * 3. Return minsum.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Initializing minsum to 0, which can give the wrong
 *    answer when all elements are positive.
 * 2. Updating minsum before calculating the new currsum.
 * 3. Using Math.max instead of Math.min.
 * 4. Confusing the minimum subarray ending at the current
 *    index with the minimum subarray across the entire array.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(n)
 * Each array element is processed once.
 *
 * Space Complexity: O(1)
 * Only two variables are maintained, excluding the input.
 *
 * Interview Reminder:
 * When a contiguous subarray problem asks for the minimum
 * sum, consider Kadane's algorithm with max replaced by min.
 *
 * ============================================================
 * IMPLEMENTATION
 * ============================================================
 */

class Solution {
    public int minSubarraySum(int[] arr) {

        int currsum = arr[0];
        int minsum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currsum = Math.min(arr[i], currsum + arr[i]);
            minsum = Math.min(currsum, minsum);
        }

        return minsum;
    }
}
```