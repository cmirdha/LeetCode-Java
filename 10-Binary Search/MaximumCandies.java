/*
 * Problem Name: Maximum Candies Allocated to K Children
 * LeetCode Number: 2226
 * Difficulty: Medium
 * Pattern: Binary Search on Answer + Greedy
 *
 * Explain Like I'm 10:
 *
 * Imagine we have several boxes of candies and k children.
 * We want to give every child the same number of candies.
 *
 * Each child can receive candies from only one box.
 *
 * We try different amounts:
 * - If enough children can receive that amount, try more.
 * - If not enough children can receive it, try less.
 *
 * Binary Search helps us find the maximum possible amount.
 *
 * Approach:
 * 1. Find the largest candy pile.
 * 2. Set low = 1 and high = largest pile.
 * 3. Calculate mid, representing candies per child.
 * 4. Count how many children can receive mid candies.
 * 5. If count >= k:
 *      - Save mid as the current answer.
 *      - Search right for a larger amount.
 * 6. Otherwise:
 *      - Search left for a smaller amount.
 * 7. Return result.
 *
 * Key Observations:
 * - Each child receives candies from only one pile.
 * - Leftover candies from a pile cannot be combined with another.
 * - candy / mid gives the number of children served by one pile.
 * - If an amount is possible, every smaller positive amount
 *   is also possible.
 * - If an amount is impossible, every larger amount is impossible.
 *
 * Complexity:
 * Time: O(N * log(maxCandy))
 * Space: O(1)
 */

class Solution {
    public int maximumCandies(int[] candies, long k) {

        // Minimum candies each child can receive
        int low=1;

        // Find the maximum pile size
        int high=0;

        // Store the maximum valid answer
        int result=0;

        // Find the largest candy pile
        for(int candy:candies){
            high=Math.max(candy,high);
        }

        // Binary Search on the number of candies per child
        while(low<=high){

            // Find the middle possible amount
            int mid=low+(high-low)/2;

            // Count how many children can receive mid candies
            long count=0;

            // Calculate children served by every pile
            for(int candy:candies){
                count+=candy/mid;
            }

            // Check whether at least k children can receive candies
            if(count>=k){

                // Current amount is possible
                result=mid;

                // Try to give each child more candies
                low=mid+1;

            }else{

                // Not enough children can receive this amount
                // Try a smaller amount
                high=mid-1;
            }
        }

        // Return the maximum candies each child can receive
        return result;
    }
}