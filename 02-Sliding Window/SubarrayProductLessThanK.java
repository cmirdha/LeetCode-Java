/*
 * Problem: Subarray Product Less Than K
 * LeetCode: #713
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Expand the window by moving the right pointer and multiply the
 *    current element with the product of the window.
 * 3. If the product becomes greater than or equal to k, shrink the
 *    window by moving the left pointer and dividing the elements
 *    leaving the window.
 * 4. For every valid window, all subarrays ending at right and
 *    starting from left to right have a product less than k.
 * 5. Add (right - left + 1) to the count for each right position.
 *
 * Explanation:
 * Since all elements are positive, when the product becomes greater
 * than or equal to k, moving the left pointer reduces the product.
 *
 * For each right pointer position, the number of valid subarrays
 * ending at right is:
 *
 *     right - left + 1
 *
 * Adding this value for every position gives the total number of
 * subarrays whose product is less than k.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {

        int left = 0;
        int product = 1;
        int count = 0;

        if (k < 1) {
            return 0;
        }

        for (int right = 0; right < nums.length; right++) {

            product = product * nums[right];

            while (product >= k && left <= right) {
                product = product / nums[left];
                left++;
            }

            count = count + right - left + 1;
        }

        return count;
    }
}