/*
 * Problem: Sort Colors
 * LeetCode: #75
 * Difficulty: Medium
 *
 * Pattern: Three Pointers / Dutch National Flag
 *
 * Approach:
 * Maintain three regions:
 *
 * [0 ... low-1]     -> 0s
 * [low ... mid-1]   -> 1s
 * [mid ... high]    -> unknown
 * [high+1 ... n-1]  -> 2s
 *
 * If nums[mid] == 0:
 *     Swap with low and move both low and mid.
 *
 * If nums[mid] == 1:
 *     Move mid.
 *
 * If nums[mid] == 2:
 *     Swap with high and move high only.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public void sortColors(int[] nums) {

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;

            } else if (nums[mid] == 1) {

                mid++;

            } else {

                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }
}