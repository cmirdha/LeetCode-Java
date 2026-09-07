package 02-Two-Pointers;

public class TwoSumII {
    
}


/*
 * Problem: Two Sum II - Input Array Is Sorted
 * LeetCode: #167
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * The array is sorted, so use two pointers:
 * one at the beginning and one at the end.
 *
 * If the sum is smaller than the target, move the left pointer.
 * If the sum is greater than the target, move the right pointer.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {

            int sum = numbers[i] + numbers[j];

            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } 
            else if (sum < target) {
                i++;
            } 
            else {
                j--;
            }
        }

        return new int[0];
    }
}