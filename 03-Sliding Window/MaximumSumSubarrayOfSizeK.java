/*
 * Problem: Maximum Sum Subarray of Size K
 * Difficulty: Easy
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * 1. Calculate the sum of the first k elements.
 * 2. Maintain a fixed-size sliding window using left and right pointers.
 * 3. When the window moves, subtract the element leaving the window.
 * 4. Add the new element entering the window.
 * 5. Keep track of the maximum sum found.
 *
 * Explanation:
 * Instead of calculating the sum of every subarray of size k from scratch,
 * use a fixed-size sliding window.
 *
 * The first window is calculated once. For every subsequent window,
 * remove the leftmost element and add the new rightmost element.
 * This allows each element to be processed only once.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int maxSubarraySum(int[] arr, int k) {

        int left = 0;
        int right = k - 1;

        int arrSum = arrSum(arr, right);
        int max = arrSum;

        while (right < arr.length - 1) {

            right++;

            arrSum = arrSum - arr[left] + arr[right];

            if (max < arrSum) {
                max = arrSum;
            }

            left++;
        }

        return max;
    }

    public int arrSum(int[] arr, int n) {

        int sum = 0;

        for (int i = 0; i <= n; i++) {
            sum = sum + arr[i];
        }

        return sum;
    }
}