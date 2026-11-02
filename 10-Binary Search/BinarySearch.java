/*
 * Problem Name: Binary Search
 * LeetCode Number: 704
 * Difficulty: Easy
 * Pattern: Binary Search
 *
 * Approach:
 * Use two pointers, low and high, to define the search space.
 * Calculate the middle index and compare nums[mid] with target.
 * Eliminate half of the search space in every iteration.
 *
 * Step-by-step Logic:
 * 1. Initialize high = nums.length - 1.
 * 2. Initialize low = 0.
 * 3. Run a while loop while high >= low.
 * 4. Calculate the middle index:
 *    mid = low + (high - low) / 2.
 * 5. If nums[mid] == target, return mid.
 * 6. If nums[mid] < target:
 *    - Target may exist in the right half.
 *    - Update low = mid + 1.
 * 7. Otherwise:
 *    - Target may exist in the left half.
 *    - Update high = mid - 1.
 * 8. If the loop finishes without finding the target,
 *    return -1.
 *
 * Key Observations:
 * - Binary Search works only when the array is sorted.
 * - Every iteration eliminates half of the search space.
 * - If nums[mid] is smaller than target, discard the left half.
 * - If nums[mid] is greater than target, discard the right half.
 * - low = mid + 1 and high = mid - 1 avoid checking
 *   the same middle element again.
 * - Using low + (high - low) / 2 prevents potential
 *   integer overflow.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n), because the search space
 *                  is divided by half in every iteration.
 * Space Complexity: O(1), because only a few variables
 *                   are used.
 */

class Solution {
    public int search(int[] nums, int target) {

        int high = nums.length-1;
        int low = 0;

        while(high>=low){
            int mid = low + (high - low) / 2;

            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid]<target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return -1;
    }
}
