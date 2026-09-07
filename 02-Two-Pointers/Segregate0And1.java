/*
 * Problem: Segregate 0s and 1s
 * Difficulty: Easy
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * Use two pointers:
 * - i starts from the beginning.
 * - j starts from the end.
 *
 * If arr[i] is 0, it is already in the correct position,
 * so move i forward.
 *
 * If arr[j] is 1, it is already in the correct position,
 * so move j backward.
 *
 * Otherwise, arr[i] is 1 and arr[j] is 0,
 * so swap them and move both pointers.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    void segregate0and1(int[] arr) {

        int i = 0;
        int j = arr.length - 1;

        while (i < j) {

            if (arr[i] == 0) {
                i++;
            }
            else if (arr[j] == 1) {
                j--;
            }
            else {
                // arr[i] == 1 && arr[j] == 0
                arr[i] = 0;
                arr[j] = 1;

                i++;
                j--;
            }
        }
    }
}