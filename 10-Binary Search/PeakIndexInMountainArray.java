/*
 * Problem Name: Peak Index in a Mountain Array
 * LeetCode Number: 852
 * Difficulty: Medium
 * Pattern: Binary Search - Peak Element
 *
 * Approach:
 * Use Binary Search to find the peak element in a mountain array.
 *
 * A mountain array first increases and then decreases.
 * Compare the middle element with its next element to determine
 * whether we are on the increasing slope or decreasing slope.
 *
 * If arr[mid] < arr[mid + 1], we are on the increasing slope,
 * so move towards the right.
 *
 * Otherwise, we are on the decreasing slope or at the peak,
 * so store mid as a potential answer and search towards the left.
 *
 * Step-by-step Logic:
 * 1. Initialize high = arr.length - 1.
 * 2. Initialize low = 0.
 * 3. Initialize peak = 0 to store the potential peak index.
 * 4. Run a while loop while low <= high.
 * 5. Calculate the middle index:
 *    mid = (low + high) / 2.
 * 6. If arr[mid] < arr[mid + 1]:
 *    - We are on the increasing slope.
 *    - The peak must be towards the right.
 *    - Update low = mid + 1.
 * 7. Otherwise:
 *    - We are on the decreasing slope or at the peak.
 *    - Store peak = mid.
 *    - Search towards the left.
 *    - Update high = mid - 1.
 * 8. When the loop finishes, return peak.
 *
 * Key Observations:
 * - A mountain array first increases and then decreases.
 * - If arr[mid] < arr[mid + 1], the peak is on the right.
 * - If arr[mid] > arr[mid + 1], the peak is at mid or on the left.
 * - We store mid as a potential answer whenever we move left.
 * - The peak is the maximum element in the mountain array.
 * - The problem guarantees a valid mountain array, so the
 *   peak cannot be at either boundary.
 *
 * Complexity Analysis:
 * Time Complexity: O(log n), because the search space is
 *                  divided by half in every iteration.
 * Space Complexity: O(1), because only a constant number
 *                   of variables are used.
 */

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int high = arr.length-1;
        int low = 0;
        int peak =0;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid]<arr[mid+1]){
                low = mid+1;
            }
            else {
                peak = mid;
                high = mid-1;
            }
        }

        return peak;


    }
}