/*
 * Problem Name: Capacity To Ship Packages Within D Days
 * LeetCode Number: 1011
 * Difficulty: Medium
 * Pattern: Binary Search on Answer + Greedy
 *
 * Explain Like I'm 10:
 *
 * Imagine we have packages with different weights.
 * We have a ship and need to deliver all packages within
 * a limited number of days.
 *
 * The ship has a maximum weight capacity.
 *
 * We want to find the smallest capacity that allows us
 * to deliver all packages within the given days.
 *
 * Approach:
 *
 * 1. Find the maximum individual package weight.
 *    This is the minimum possible ship capacity.
 *
 * 2. Calculate the sum of all package weights.
 *    This is the maximum possible ship capacity.
 *
 * 3. Apply Binary Search between low and high.
 *
 * 4. For every mid capacity, call ifShippingPossible().
 *
 * 5. If shipping is possible:
 *      - Save mid as the current answer.
 *      - Search left for a smaller capacity.
 *
 * 6. If shipping is not possible:
 *      - Search right for a larger capacity.
 *
 * 7. Return result.
 *
 * Key Observations:
 *
 * - Packages must be shipped in their original order.
 * - A package cannot be divided between different days.
 * - The ship must carry at least the heaviest package.
 * - The sum of all packages is the maximum possible capacity.
 * - If a capacity works, every larger capacity also works.
 * - If a capacity fails, every smaller capacity also fails.
 *
 * Complexity:
 *
 * N = Number of packages
 * S = Sum of all package weights
 *
 * Time: O(N * log S)
 * Space: O(1)
 */

class Solution {
    public int shipWithinDays(int[] weights, int days) {

        // Minimum capacity must be at least the heaviest package
        int low =0;

        // Maximum capacity can be the sum of all packages
        int high =0;

        // Store the minimum valid capacity
        int result =0;

        // Calculate the search boundaries
        for(int weight :weights){

            // Find the heaviest package
            low = Math.max(low, weight);

            // Calculate total weight of all packages
            high +=weight;
        }

        // Binary Search for minimum valid ship capacity
        while(low<=high){

            // Calculate the middle capacity
            int mid = (low+high)/2;

            // Check whether this capacity can ship packages in time
            if(ifShippingPossible( weights, days, mid)){

                // Current capacity is valid
                result = mid;

                // Try to find a smaller valid capacity
                high = mid-1;

            }
            else

                // Capacity is too small, increase it
                low = mid+1;
        }

        // Return minimum required ship capacity
        return result;

    }


    public boolean ifShippingPossible(int[] weights, int days, int ship) {

        // Start with the first day
        int daysUsed = 1;

        // No packages loaded initially
        int currentLoad = 0;

        // Process packages in their original order
        for (int weight : weights) {

            // Check whether adding this package exceeds capacity
            if (currentLoad + weight > ship) {

                // Start a new day
                daysUsed++;

                // Put the current package on the new day
                currentLoad = weight;

            } else {

                // Package fits, add it to the current day's load
                currentLoad += weight;
            }
        }

        // Check whether all packages were shipped within allowed days
        return daysUsed <= days;
    }


}