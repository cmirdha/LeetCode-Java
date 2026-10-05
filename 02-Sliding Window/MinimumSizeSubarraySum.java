/*
 * Problem: Minimum Size Subarray Sum
 * LeetCode: #209
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Expand the window by moving the right pointer and add each element
 *    to the current window sum.
 * 3. Whenever the sum becomes greater than or equal to the target,
 *    calculate the current window size.
 * 4. Update the minimum window length found so far.
 * 5. Shrink the window by removing the element at the left pointer
 *    and move the left pointer forward.
 * 6. Continue shrinking while the window sum is greater than or equal
 *    to the target.
 * 7. If no valid subarray exists, return 0.
 *
 * Explanation:
 * The sliding window is expanded until its sum reaches or exceeds
 * the target. Once the target is reached, we try to make the window
 * smaller by moving the left pointer.
 *
 * This allows us to find the smallest possible contiguous subarray
 * whose sum is greater than or equal to the target without checking
 * every possible subarray.
 *
 * The current window size is:
 *
 *      right - left + 1
 *
 * The minimum valid window size is stored as the answer.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;
        int output = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            sum = sum + nums[right];

            while (sum >= target) {

                int windowSize = right - left + 1;
                output = Math.min(windowSize, output);

                sum -= nums[left];
                left++;
            }
        }

        return output == Integer.MAX_VALUE ? 0 : output;
    }
}