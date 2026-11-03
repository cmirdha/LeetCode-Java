/*
 * Problem Name: Count Occurrences in a Sorted Array
 * LeetCode Number: N/A (GeeksforGeeks Problem)
 * Difficulty: Easy
 * Pattern: Binary Search - First and Last Occurrence
 *
 * Approach:
 * Use Binary Search twice to find the first and last
 * occurrences of the target element.
 *
 * Calculate the total frequency using:
 * result = lastelement - firstelement + 1.
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
 * 7. Otherwise:
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
 * 13. Otherwise:
 *     - Store lastelement = mid.
 *     - Continue searching the right half.
 *     - Update low = mid + 1.
 *
 * Calculate Frequency:
 * 14. Initialize result = 0.
 * 15. If both firstelement and lastelement are -1,
 *     the target does not exist. Return 0.
 * 16. Otherwise, calculate:
 *     result = lastelement - firstelement + 1.
 * 17. Return result.
 *
 * Key Observations:
 * - The array is sorted, so Binary Search can be applied.
 * - First Binary Search finds the first occurrence.
 * - Second Binary Search finds the last occurrence.
 * - The difference between the last and first indices,
 *   plus one, gives the total frequency.
 * - If the target is absent, return 0.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n) + O(log n) = O(log n),
 *                  because Binary Search is performed twice.
 * Space Complexity: O(1), because only a constant number
 *                   of variables are used.
 */

class Solution {
    int countFreq(int[] nums, int target) {
        // code here


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

        int result =0;
        if( firstelement ==-1 && lastelement ==-1)
            return result;

        result = lastelement- firstelement+1;
        return result;
    }
}