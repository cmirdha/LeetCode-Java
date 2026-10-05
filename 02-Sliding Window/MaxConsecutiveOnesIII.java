/*
 * Problem: Max Consecutive Ones III
 * LeetCode: #1004
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Use two pointers, left and right, to maintain a sliding window.
 * 2. Use a HashMap to keep track of the frequency of 0s and 1s
 *    inside the current window.
 * 3. Expand the window by moving the right pointer.
 * 4. If the number of zeros becomes greater than k, shrink the window
 *    by moving the left pointer.
 * 5. Decrease the frequency of the element leaving the window and
 *    remove it from the HashMap when its frequency becomes 0.
 * 6. For every valid window, calculate its length and update the
 *    maximum length.
 *
 * Explanation:
 * We are allowed to flip at most k zeros into ones.
 *
 * Therefore, a window is valid as long as it contains at most k zeros.
 * When the number of zeros becomes greater than k, we move the left
 * pointer until the window becomes valid again.
 *
 * The length of the current valid window is:
 *
 *      right - left + 1
 *
 * The maximum valid window length is the answer.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int longestOnes(int[] nums, int k) {

        int output = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;

        for (int right = 0; right < nums.length; right++) {

            map.put(
                    nums[right],
                    map.getOrDefault(nums[right], 0) + 1
            );

            if (map.getOrDefault(0, 0) > k) {

                map.put(
                        nums[left],
                        map.get(nums[left]) - 1
                );

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++;
            }

            int windowSize = right - left + 1;
            output = Math.max(windowSize, output);
        }

        return output;
    }
}