/*
 * Problem Name: Find First and Last Position of Element in Sorted Array
 * LeetCode Number: 34
 * Difficulty: Medium
 * Pattern: Binary Search - Lower Bound and Upper Bound
 *
 * Approach:
 * Use Binary Search twice to find the first and last
 * occurrences of the target element.
 *
 * In the first Binary Search, whenever the target is found,
 * store its index and continue searching in the left half.
 *
 * In the second Binary Search, whenever the target is found,
 * store its index and continue searching in the right half.
 *
 * Step-by-step Logic:
 * 1. Initialize low = 0 and high = nums.length - 1.
 * 2. Initialize firstelement = -1 and lastelement = -1.
 *
 * First Binary Search - Find First Occurrence:
 * 3. Run a while loop while low <= high.
 * 4. Calculate mid = (low + high) / 2.
 * 5. If nums[mid] < target:
 *    - Search the right half.
 *    - Update low = mid + 1.
 * 6. If nums[mid] > target:
 *    - Search the left half.
 *    - Update high = mid - 1.
 * 7. Otherwise, the target is found:
 *    - Store firstelement = mid.
 *    - Continue searching the left half.
 *    - Update high = mid - 1.
 *
 * Second Binary Search - Find Last Occurrence:
 * 8. Reset low = 0 and high = nums.length - 1.
 * 9. Run another while loop while low <= high.
 * 10. Calculate mid = (low + high) / 2.
 * 11. If nums[mid] < target:
 *     - Search the right half.
 *     - Update low = mid + 1.
 * 12. If nums[mid] > target:
 *     - Search the left half.
 *     - Update high = mid - 1.
 * 13. Otherwise, the target is found:
 *     - Store lastelement = mid.
 *     - Continue searching the right half.
 *     - Update low = mid + 1.
 * 14. Create the result array containing:
 *     {firstelement, lastelement}.
 * 15. Return the result array.
 *
 * Key Observations:
 * - The array is sorted, so Binary Search can be applied.
 * - Finding the first occurrence requires moving left
 *   whenever the target is found.
 * - Finding the last occurrence requires moving right
 *   whenever the target is found.
 * - We use two independent Binary Search operations.
 * - If the target does not exist, both variables remain -1.
 * - This approach avoids traversing the array linearly.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n) + O(log n) = O(log n),
 *                  because Binary Search is performed twice.
 * Space Complexity: O(1), because only a constant number
 *                   of variables are used.
 */

class Solution {
    public int[] searchRange(int[] nums, int target) {


        int low =0;
        int high = nums.length-1;
        int firstelement = -1;
        int lastelement = -1;


        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]<target){
                low = mid+1;
            }
            else if(nums[mid] > target){
                high = mid-1;
            }
            else{
                firstelement = mid;
                high =mid-1;
            }
        }
        low =0;
        high = nums.length-1;

        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid]<target){
                low = mid+1;
            }
            else if(nums[mid] > target){
                high = mid-1;
            }
            else{
                lastelement = mid;
                low = mid+1;
            }
        }

        int [] result = new int[] {firstelement,lastelement};



        return result;

    }
}