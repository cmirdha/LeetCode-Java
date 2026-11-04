/*
 * Problem Name: Find Minimum in Rotated Sorted Array
 * LeetCode Number: 153
 * Difficulty: Medium
 * Pattern: Binary Search - Rotated Sorted Array
 *
 * Approach:
 * Use Binary Search to find the minimum element in a
 * rotated sorted array.
 *
 * Compare nums[mid] with nums[high] to determine which
 * half contains the minimum element.
 *
 * If nums[mid] > nums[high], the minimum element must
 * exist in the right half.
 *
 * Otherwise, the minimum element is either at mid or
 * somewhere in the left half.
 *
 * Step-by-step Logic:
 * 1. Initialize high = nums.length - 1.
 * 2. Initialize low = 0.
 * 3. Run a while loop while low < high.
 * 4. Calculate the middle index:
 *    mid = (low + high) / 2.
 * 5. Compare nums[mid] with nums[high].
 * 6. If nums[mid] > nums[high]:
 *    - The right half contains the minimum element.
 *    - Update low = mid + 1.
 * 7. Otherwise:
 *    - The minimum element is at mid or towards the left.
 *    - Update high = mid.
 * 8. Continue until low and high point to the same index.
 * 9. Return nums[high].
 *
 * Key Observations:
 * - The array was originally sorted in ascending order.
 * - Rotation creates two sorted portions.
 * - Comparing nums[mid] with nums[high] helps identify
 *   which portion contains the minimum element.
 * - If nums[mid] > nums[high], mid cannot be the minimum.
 * - If nums[mid] <= nums[high], mid could be the minimum,
 *   so we retain mid by updating high = mid.
 * - The loop uses low < high because we are narrowing
 *   the search space until both pointers meet.
 * - At the end, low == high, pointing to the minimum element.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n), because the search space
 *                  is divided by half in every iteration.
 * Space Complexity: O(1), because only a constant number
 *                   of variables are used.
 */

class Solution {
    public int findMin(int[] nums) {
        int high = nums.length-1;
        int low = 0;

        while(low<high){
            int mid = (low+high)/2;
            if(nums[mid]> nums[high]){
                low = mid+1;
            }
            else{
                high = mid;
            }
        }

        return nums[high];



    }
}