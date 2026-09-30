/*
 * Problem: Remove Duplicates from Sorted Array II
 * LeetCode: #80
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * Since the array is sorted, each element can appear at most twice.
 *
 * - write points to the position where the next valid element should go.
 * - read scans through the array.
 * - The first two elements are always allowed.
 * - For every later element, compare it with nums[write - 2].
 *
 * If nums[read] != nums[write - 2], the current element can be kept.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int removeDuplicates(int[] nums) {

        int write = 0;

        for (int read = 0; read < nums.length; read++) {

            // First two occurrences are always allowed
            if (write < 2) {
                nums[write] = nums[read];
                write++;
            }
            // Allow an element only if it doesn't create
            // three consecutive duplicates
            else if (nums[read] != nums[write - 2]) {
                nums[write] = nums[read];
                write++;
            }
        }

        return write;
    }
}