```java
/*
 * Problem: Maximum Product Subarray
 * LeetCode: #152
 * Difficulty: Medium
 * Pattern: Kadane's Algorithm Variant (Dynamic Programming)
 *
 * ============================================================
 * APPROACH / HOW TO THINK
 * ============================================================
 *
 * For Maximum Sum Subarray, we track the maximum subarray
 * sum ending at the current index.
 *
 * For Maximum Product Subarray, tracking only the maximum
 * product is not enough.
 *
 * Reason:
 * Negative × Negative = Positive
 *
 * A very small negative product can become the largest
 * positive product when multiplied by another negative number.
 *
 * Therefore, maintain three variables:
 *
 * maxending -> Largest product ending at the current index.
 * minending -> Smallest product ending at the current index.
 * maxprod   -> Largest product found anywhere so far.
 *
 * ============================================================
 * THREE POSSIBLE TRANSITIONS
 * ============================================================
 *
 * For every element nums[i], consider:
 *
 * 1. Start a new subarray:
 *
 *        temp = nums[i]
 *
 * 2. Extend the previous maximum product:
 *
 *        max = maxending * nums[i]
 *
 * 3. Extend the previous minimum product:
 *
 *        min = minending * nums[i]
 *
 * Calculate the new maximum product ending here:
 *
 *        maxending = max(temp, max(max, min))
 *
 * Calculate the new minimum product ending here:
 *
 *        minending = min(temp, min(max, min))
 *
 * Update the global answer:
 *
 *        maxprod = max(maxprod, maxending, minending)
 *
 * The temporary variables preserve the previous maximum
 * and minimum products before either state is updated.
 *
 * ============================================================
 * KEY OBSERVATION
 * ============================================================
 *
 * The previous minimum product is just as important as
 * the previous maximum product.
 *
 * Example:
 *
 *        Previous minimum = -6
 *        Current element  = -4
 *
 *        (-6) * (-4) = 24
 *
 * The previous minimum becomes the new maximum because
 * multiplying two negative numbers produces a positive.
 *
 * ============================================================
 * MENTAL TEMPLATE
 * ============================================================
 *
 * 1. Initialize all three variables with nums[0].
 *
 *        maxprod = nums[0]
 *        maxending = nums[0]
 *        minending = nums[0]
 *
 * 2. For every remaining element:
 *
 *        temp = nums[i]
 *        max = maxending * nums[i]
 *        min = minending * nums[i]
 *
 * 3. Update the maximum and minimum products:
 *
 *        maxending = max(temp, max(max, min))
 *        minending = min(temp, min(max, min))
 *
 * 4. Update the global maximum product.
 *
 * 5. Return maxprod.
 *
 * ============================================================
 * COMMON MISTAKES
 * ============================================================
 *
 * 1. Treating this exactly like Maximum Sum Subarray.
 * 2. Tracking only the maximum product.
 * 3. Forgetting that minending * nums[i] can become
 *    the new maximum when both values are negative.
 * 4. Updating maxending before calculating the new minending
 *    without preserving the previous values.
 * 5. Initializing the answer to zero, which fails when
 *    every array element is negative.
 *
 * ============================================================
 * INTERVIEW TRIGGER
 * ============================================================
 *
 * Whenever a problem involves:
 *
 *    - Product
 *    - Contiguous subarray
 *    - Negative numbers
 *
 * Think:
 *
 * "Track both maxEndingHere and minEndingHere."
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(n)
 * Each element is processed once.
 *
 * Space Complexity: O(1)
 * Only a fixed number of variables are maintained.
 *
 * ============================================================
 * IMPLEMENTATION
 * ============================================================
 */

class Solution {
    public int maxProduct(int[] nums) {

        // Track both maximum and minimum products ending here.
        int maxprod = nums[0];
        int maxending = nums[0];
        int minending = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int temp = nums[i];
            int max = maxending * nums[i];
            int min = minending * nums[i];

            // Calculate the new maximum and minimum products.
            maxending = Math.max(temp, Math.max(max, min));
            minending = Math.min(temp, Math.min(max, min));

            // Update the best product found so far.
            maxprod = Math.max(maxprod,
                    Math.max(maxending, minending));
        }

        return maxprod;
    }
}
```