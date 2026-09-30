/*
 * Problem: Count Triplets with Sum Smaller Than a Given Value
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 *
 * Pattern: Two Pointers
 *
 * Approach:
 * 1. Sort the array.
 * 2. Fix the first element using x.
 * 3. Use two pointers y and z for the remaining elements.
 *
 * If arr[x] + arr[y] + arr[z] < sum:
 *     Since the array is sorted, every element between y and z
 *     will also form a valid triplet with x and y.
 *
 *     Therefore, there are (z - y) valid triplets.
 *
 * Otherwise, decrease z to reduce the sum.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(log n) to O(n), depending on sorting implementation.
 */

class Solution {

    int countTriplets(int sum, int arr[]) {

        Arrays.sort(arr);

        int result = 0;

        for (int x = 0; x < arr.length - 2; x++) {

            int y = x + 1;
            int z = arr.length - 1;

            while (y < z) {

                int currentSum = arr[x] + arr[y] + arr[z];

                if (currentSum >= sum) {
                    z--;
                } else {
                    result += z - y;
                    y++;
                }
            }
        }

        return result;
    }
}