/*
 * Problem: Shortest Unsorted Continuous Subarray
 * LeetCode: #581
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * Find the initial unsorted boundaries, determine the minimum
 * and maximum values within that range, then expand the boundaries
 * if required.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        boolean isfound = false;

        if (nums.length == 1) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                left = i - 1;
                isfound = true;
                break;
            }
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i - 1]) {
                right = i;
                break;
            }
        }

        int min = nums[left];
        int max = nums[right];

        for (int i = left; i <= right; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        if (isfound == false) {
            return 0;
        }

        while (left > 0 && nums[left - 1] > min) {
            left--;
        }

        while (right < nums.length - 1 && nums[right + 1] < max) {
            right++;
        }

        return right - left + 1;

    }
}
