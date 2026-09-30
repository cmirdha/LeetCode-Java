/*
 * Problem: Remove Duplicates from Sorted Array
 * LeetCode: #26
 * Difficulty: Easy
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * Use two pointers:
 * - x points to the position of the last unique element.
 * - y scans through the array.
 *
 * When nums[y] is different from nums[x], a new unique element
 * is found. Move x forward and place nums[y] at that position.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int removeDuplicates(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int x = 0;
        int y = 1;

        while (y < nums.length) {

            if (nums[y] != nums[x]) {
                x++;
                nums[x] = nums[y];
            }

            y++;
        }

        return x + 1;
    }
}