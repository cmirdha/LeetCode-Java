/*
 * Problem Name: Aggressive Cows
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 * Pattern: Binary Search on Answer + Greedy
 *
 * Explain Like I'm 10:
 *
 * Imagine we have several houses in a straight line and some cows.
 * We need to place cows in different houses while keeping them
 * as far away from each other as possible.
 *
 * We try different minimum distances:
 * - If all cows can be placed, try a bigger distance.
 * - If all cows cannot be placed, try a smaller distance.
 *
 * Binary Search helps us find the biggest distance that works.
 *
 * Approach:
 * 1. Sort the array.
 * 2. Set low = 1 and high = last position - first position.
 * 3. Find mid and check whether all cows can be placed.
 * 4. If possible, save the answer and search right.
 * 5. If impossible, search left.
 * 6. Return the maximum valid distance.
 *
 * Key Observations:
 * - We need to maximize the minimum distance.
 * - Greedy placement chooses the earliest valid stall.
 * - If a distance is possible, smaller distances are also possible.
 * - If a distance is impossible, larger distances are also impossible.
 *
 * Complexity:
 * Sorting: O(N log N)
 * Binary Search + Helper: O(N log D)
 * Total: O(N log N + N log D)
 * Helper extra space: O(1)
 */

class Solution {
    public int aggressiveCows(int[] arr, int k) {
        // Sort stalls from smallest position to largest position
        Arrays.sort(arr);

        // Minimum possible distance
        int low = 1;

        // Maximum possible distance
        int high = arr[arr.length-1] - arr[0];

        // Store the best valid answer
        int result =0;

        // Continue while search space is valid
        while(low<=high){

            // Test the middle distance
            int mid = (low+high)/2;

            // Check whether all cows can be placed
            if(isPlaced(arr, mid, k)){

                // Current distance is possible
                result = mid;

                // Try to find a bigger distance
                low = mid + 1;     // go right

            }else{

                // Current distance is not possible
                // Try a smaller distance
                high = mid - 1;    // go left
            }

        }

        // Return maximum possible minimum distance
        return result;

    }

    public boolean isPlaced(int [] arr, int dist, int no){

        // Place first cow at the first stall.
        // Calculate the minimum position for the next cow.
        int sum = arr[0]+dist;

        // One cow is already placed
        no--;

        // Check remaining stalls
        for(int i =1;i<arr.length;i++){

            // Check whether current stall is far enough
            if(arr[i]>=sum){

                // Place another cow
                no--;

                // Calculate minimum position for the next cow
                sum= arr[i]+dist;

                // If no cows remain, placement is successful
                if(no == 0)
                    return true ;
            }
        }

        // Not enough cows could be placed
        return false;

    }
}