/*
 * Problem: Happy Number
 * LeetCode: #202
 * Difficulty: Easy
 *
 * Pattern: Fast & Slow Pointers
 *
 * Approach:
 * 1. Calculate the sum of the squares of all digits of n.
 * 2. Replace n with the calculated sum.
 * 3. Repeat the process until n becomes either 1 or 4.
 * 4. If n becomes 1, the number is a Happy Number.
 * 5. If n becomes 4, the process has entered a cycle, so the number
 *    is not a Happy Number.
 *
 * Explanation:
 * For a happy number, repeatedly replacing the number with the sum
 * of the squares of its digits eventually results in 1.
 *
 * For example:
 *
 *      19 -> 1² + 9² = 82
 *      82 -> 8² + 2² = 68
 *      68 -> 6² + 8² = 100
 *      100 -> 1² + 0² + 0² = 1
 *
 * Therefore, 19 is a happy number.
 *
 * If the process does not reach 1, it eventually enters a cycle.
 * The cycle contains 4, so reaching 4 indicates that the number is
 * not happy.
 *
 * Time Complexity: O(log n) per transformation, with a bounded number
 * of transformations.
 *
 * Space Complexity: O(1)
 */

class Solution {

    public boolean isHappy(int n) {

        while (n != 4 && n != 1) {

            int sum = 0;

            while (n != 0) {

                int digit = n % 10;
                sum = sum + digit * digit;
                n = n / 10;
            }

            n = sum;
        }

        return n == 1;
    }
}