
/*
 * Problem Name: Find Pivot Index
 * LeetCode Number: 724
 * Difficulty: Easy
 * Pattern: Prefix Sum
 *
 * ------------------------------------------------------------
 * APPROACH: TOTAL SUM + PREFIX SUM
 * ------------------------------------------------------------
 *
 * The goal is to find an index where the sum of all elements
 * strictly to its left equals the sum of all elements
 * strictly to its right.
 *
 * The pivot element itself is excluded from both sums.
 *
 * If multiple pivot indices exist, return the leftmost one.
 * If no pivot index exists, return -1.
 *
 * ------------------------------------------------------------
 * STEP 1: CALCULATE THE TOTAL ARRAY SUM
 * ------------------------------------------------------------
 *
 * Variable:
 * - sum: Sum of all elements in the array.
 *
 * Traverse the array once and add every element to sum.
 *
 * After this loop:
 *
 * sum = nums[0] + nums[1] + ... + nums[n - 1]
 *
 * This total lets us calculate the right-side sum without
 * traversing the right side separately for every index.
 *
 * ------------------------------------------------------------
 * STEP 2: CALCULATE THE PREFIX SUM
 * ------------------------------------------------------------
 *
 * Variable:
 * - prefixsum: Sum of all elements strictly to the left
 *   of the current index.
 *
 * At index i:
 *
 * prefixsum = nums[0] + ... + nums[i - 1]
 *
 * For index 0, there are no elements to the left, so:
 *
 * prefixsum = 0
 *
 * In the code, prefixsum is updated using nums[i - 1].
 * This ensures that the current element is not included
 * in the left-side sum.
 *
 * ------------------------------------------------------------
 * STEP 3: CALCULATE THE SUFFIX SUM
 * ------------------------------------------------------------
 *
 * Variable:
 * - suffixsum: Sum of all elements strictly to the right
 *   of the current index.
 *
 * Since sum contains the entire array, subtract the prefix
 * sum and the current element:
 *
 * suffixsum = sum - prefixsum - nums[i]
 *
 * Why does this work?
 *
 * Total sum = Left sum + Current element + Right sum
 *
 * Therefore:
 *
 * Right sum = Total sum - Left sum - Current element
 *
 * ------------------------------------------------------------
 * STEP 4: CHECK THE PIVOT CONDITION
 * ------------------------------------------------------------
 *
 * If:
 *
 * prefixsum == suffixsum
 *
 * the current index is a pivot index, so return i.
 *
 * We scan from left to right and return immediately when
 * the condition is satisfied. Therefore, the first matching
 * index is the leftmost pivot index.
 *
 * If the loop finishes without finding a pivot, return -1.
 *
 * ------------------------------------------------------------
 * EXAMPLE WALKTHROUGH
 * ------------------------------------------------------------
 *
 * Input:
 * nums = [1, 7, 3, 6, 5, 6]
 *
 * Total sum = 28
 *
 * Index 0:
 * Left sum  = 0
 * Right sum = 28 - 0 - 1 = 27
 * Not a pivot.
 *
 * Index 1:
 * Left sum  = 1
 * Right sum = 28 - 1 - 7 = 20
 * Not a pivot.
 *
 * Index 2:
 * Left sum  = 1 + 7 = 8
 * Right sum = 28 - 8 - 3 = 17
 * Not a pivot.
 *
 * Index 3:
 * Left sum  = 1 + 7 + 3 = 11
 * Right sum = 28 - 11 - 6 = 11
 * Pivot found. Return 3.
 *
 * Output:
 * 3
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. A separate suffix-sum array is unnecessary because
 *    the total sum can be used to calculate each right sum.
 *
 * 2. The pivot element must be excluded from both the
 *    prefix and suffix sums.
 *
 * 3. The prefix sum represents only elements before i.
 *    Update it with nums[i] after checking index i, or use
 *    nums[i - 1] before checking the current index.
 *
 * 4. Returning the first matching index guarantees the
 *    leftmost pivot index.
 *
 * 5. Negative numbers and zero are supported because the
 *    approach relies on addition and subtraction only.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 * - First traversal calculates the total sum: O(n).
 * - Second traversal checks each pivot index: O(n).
 * - Overall: O(n).
 *
 * Space Complexity: O(1)
 * Only a few integer variables are used; no extra array
 * or collection is required.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

class Solution {
    public int pivotIndex(int[] nums) {

        // Step 1: Calculate the total sum of the array.
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }

        // Step 2: Track the sum of elements to the left.
        int prefixsum = 0;

        for (int i = 0; i < nums.length; i++) {

            // At index 0, there are no elements on the left.
            // For later indices, include the previous element
            // in the prefix sum.
            if (i == 0) {
                prefixsum = 0;
            } else {
                prefixsum += nums[i - 1];
            }

            // Calculate the sum strictly to the right.
            int suffixsum = sum - prefixsum - nums[i];

            // The current index is the pivot if both sums match.
            if (prefixsum == suffixsum) {
                return i;
            }
        }

        // No pivot index exists.
        return -1;
    }
}
