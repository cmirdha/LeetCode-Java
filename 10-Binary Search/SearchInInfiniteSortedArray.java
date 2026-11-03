/*
 * Problem Name: Search in an Infinite Sorted Array
 * LeetCode Number: N/A (GeeksforGeeks Problem)
 * Difficulty: Medium
 * Pattern: Binary Search - Exponential Search
 *
 * Approach:
 * Since the array is considered to be of infinite size,
 * we cannot directly determine the search boundaries.
 *
 * First, use Exponential Search to identify a search range
 * where the target might exist.
 *
 * Start with a small range and keep doubling its size
 * until the target becomes smaller than or equal to
 * the element at the end index.
 *
 * Once the range is identified, apply Binary Search
 * within that range to find the target.
 *
 * Step-by-step Logic:
 *
 * Part 1: Find Position Using Exponential Search
 *
 * 1. Initialize start = 0 and end = 1.
 * 2. Run a while loop while:
 *    end < arr.size() && target > arr.get(end).
 * 3. Store the next starting position:
 *    temp = end + 1.
 * 4. Double the search range:
 *    end = end + (end - start + 1) * 2.
 * 5. If end exceeds the array boundary,
 *    update end = arr.size() - 1.
 * 6. Update start = temp.
 * 7. Continue until the target is within the identified
 *    search range or the array boundary is reached.
 *
 * Part 2: Binary Search
 *
 * 8. Pass arr, target, start and end to binarySearch().
 * 9. Run a while loop while start <= end.
 * 10. Calculate the middle index:
 *     mid = start + (end - start) / 2.
 * 11. If target < arr.get(mid):
 *     - Search the left half.
 *     - Update end = mid - 1.
 * 12. If target > arr.get(mid):
 *     - Search the right half.
 *     - Update start = mid + 1.
 * 13. Otherwise, the target is found.
 *     Return mid.
 * 14. If the target is not found, return -1.
 *
 * Key Observations:
 * - Exponential Search is used to find the search boundaries.
 * - The search range grows exponentially.
 * - The range is doubled in every iteration.
 * - Once the range is identified, Binary Search is applied.
 * - Exponential Search is useful when the target is located
 *   at an unknown position in a sorted array.
 * - The given implementation uses ArrayList to simulate
 *   an infinite array with a finite collection.
 *
 * Complexity Analysis:
 * Time Complexity: O(log p), where p is the position
 *                  of the target in the array.
 * Space Complexity: O(1), because only a constant number
 *                   of variables are used.
 */

import java.util.*;

public class GfG {

    public static int binarySearch(ArrayList<Integer> arr, int target, int start, int end) {
        // Perform binary search within the range [start, end]
        while (start <= end) {
            // Calculate the mid index
            int mid = start + (end - start) / 2;

            // If target is smaller, search the left half
            if (target < arr.get(mid)) {
                end = mid - 1;
            }
            // If target is larger, search the right half
            else if (target > arr.get(mid)) {
                start = mid + 1;
            }
            // If target is found, return the index
            else {
                return mid;
            }
        }
        // If the target is not found, return -1
        return -1;
    }

    public static int findPos(ArrayList<Integer> arr, int target) {
        // Initialize start and end for the search range
        int start = 0;
        int end = 1;

        // Keep doubling the search range until the target
        // is within the range
        while (end < arr.size() && target > arr.get(end)) {
            // Temporarily store the current end
            // as new start
            int temp = end + 1;

            // Double the box size and update the end index
            end = end + (end - start + 1) * 2;

            // Clamp end if it goes out of array bounds
            if (end >= arr.size())
                end = arr.size() - 1;

            // Update start to the old end + 1
            start = temp;
        }

        // Perform binary search within the found range
        return binarySearch(arr, target, start, end);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170));

        int target = 170;
        int ans = findPos(arr, target);
        System.out.println(ans);
    }
}