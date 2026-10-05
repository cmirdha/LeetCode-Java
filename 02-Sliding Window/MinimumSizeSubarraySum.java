/*
 * Problem: Minimum Size Subarray Sum
 * LeetCode: #209
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Expand the window by moving the right pointer until the window sum
 *    becomes greater than or equal to the target.
 * 3. Once the target is reached, calculate the current window length.
 * 4. Update the minimum length found so far.
 * 5. Shrink the window by moving the left pointer and subtracting
 *    the element leaving the window.
 * 6. Continue until all possible windows have been checked.
 * 7. Return 0 if no valid subarray is found.
 *
 * Explanation:
 * The sliding window technique avoids checking every possible subarray.
 * The right pointer expands the window to reach the target sum, while
 * the left pointer shrinks the window to find the smallest possible
 * subarray that still satisfies the target.
 *
 * Since all elements in the array are positive, removing elements from
 * the left always decreases the sum, which makes the sliding window
 * approach possible.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int right = 0;
        int sum = nums[0];
        int minLen = Integer.MAX_VALUE;

        while (right < nums.length) {

            while (sum < target && right < nums.length - 1) {
                right++;
                sum = sum + nums[right];
            }

            if (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
            }

            if (left >= nums.length) {
                break;
            }

            sum = sum - nums[left];
            left++;

            if (left > right && left < nums.length) {
                right = left;
                sum = nums[left];
            }

            if (sum < target && right == nums.length - 1) {
                break;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}