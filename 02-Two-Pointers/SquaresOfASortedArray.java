/*
 * Problem: Squares of a Sorted Array
 * LeetCode: #977
 * Difficulty: Easy
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * The input array is sorted, but negative numbers can have
 * larger squares than positive numbers.
 *
 * Compare the square of the leftmost and rightmost elements.
 * Place the larger square at the end of the result array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int[] sortedSquares(int[] nums) {

        int[] result = new int[nums.length];

        int i = 0;
        int j = nums.length - 1;
        int pos = nums.length - 1;

        while (i <= j) {

            int leftSquare = nums[i] * nums[i];
            int rightSquare = nums[j] * nums[j];

            if (rightSquare >= leftSquare) {
                result[pos] = rightSquare;
                j--;
            } else {
                result[pos] = leftSquare;
                i++;
            }

            pos--;
        }

        return result;
    }
}