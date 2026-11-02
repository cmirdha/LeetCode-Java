/*
 * Problem Name: Ceil in a Sorted Array
 * LeetCode Number: N/A (GeeksforGeeks Problem)
 * Difficulty: Easy
 * Pattern: Binary Search - Lower Bound
 *
 * Approach:
 * Use Binary Search to find the smallest element in the
 * sorted array that is greater than or equal to x.
 *
 * Maintain an ans variable to store the index of the
 * potential ceil element.
 *
 * Whenever nums[mid] >= x, store mid as a potential answer
 * and search the left half to find a smaller valid index.
 *
 * Step-by-step Logic:
 * 1. Initialize low = 0.
 * 2. Initialize high = nums.length - 1.
 * 3. Initialize ans = -1 because initially no valid
 *    ceil index is found.
 * 4. Run a while loop while low <= high.
 * 5. Calculate the middle index:
 *    mid = low + (high - low) / 2.
 * 6. If nums[mid] >= x:
 *    - nums[mid] is a potential ceil element.
 *    - Store its index: ans = mid.
 *    - Search the left half for a smaller valid index.
 *    - Update high = mid - 1.
 * 7. Otherwise:
 *    - nums[mid] < x.
 *    - The current element cannot be the ceil.
 *    - Search the right half.
 *    - Update low = mid + 1.
 * 8. When the loop finishes, return ans.
 *
 * Key Observations:
 * - Ceil means the smallest element greater than or equal
 *   to the given target x.
 * - We are searching for the first element >= x.
 * - Whenever nums[mid] >= x, we store mid as a potential
 *   answer and continue searching towards the left.
 * - If nums[mid] < x, all elements on the left are also
 *   smaller because the array is sorted.
 * - ans = -1 indicates that no valid ceil element exists.
 * - This approach is also known as Lower Bound Binary Search.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n), because the search space
 *                  is divided by half in every iteration.
 * Space Complexity: O(1), because only a few variables
 *                   are used.
 */

class Solution {
    public int findCeil(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= x) {
                ans = mid;
                high = mid - 1; // look for a smaller valid ceil
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}