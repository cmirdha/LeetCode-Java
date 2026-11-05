/*
 * Problem Name: H-Index II
 * LeetCode Number: 275
 * Difficulty: Medium
 * Pattern: Binary Search
 *
 * Explain Like I'm 10:
 *
 * Imagine you have several research papers arranged according
 * to their citation counts.
 *
 * We need to find the biggest number H such that at least H
 * papers have H or more citations.
 *
 * Because the array is sorted, Binary Search helps us find
 * the answer efficiently.
 *
 * Approach:
 *
 * 1. Initialize low = 0 and high = n - 1.
 * 2. Calculate mid.
 * 3. Calculate the number of papers from mid to the end:
 *       n - mid
 * 4. Check whether citations[mid] >= n - mid.
 * 5. If true:
 *       - Save n - mid as the answer.
 *       - Search left for a potentially bigger H-index.
 * 6. Otherwise:
 *       - Search right for a smaller number of qualifying papers.
 * 7. Return result.
 *
 * Key Observations:
 *
 * - The array must be sorted in ascending order.
 * - n - mid represents the number of papers from mid onward.
 * - A valid candidate is found when citations[mid] >= n - mid.
 * - We search left after finding a valid candidate because
 *   a smaller index means more qualifying papers.
 *
 * Complexity:
 * Time: O(log N)
 * Space: O(1)
 */

class Solution {
    public int hIndex(int[] citations) {

        // Start Binary Search from the first index
        int low =0;

        // Last valid index
        int high = citations.length-1;

        // Total number of papers
        int n =citations.length;

        // Store the best valid H-index
        int result = 0;

        // Continue while search space is valid
        while(low<=high){

            // Find middle index
            int mid = (low + high)/2;

            // Check whether the remaining papers have enough citations
            if(citations[mid]>=n-mid){

                // Current H-index candidate is valid
                result = n - mid;

                // Search left for a potentially larger H-index
                high = mid-1;

            }
            else{

                // Not enough citations, search right
                low = mid+1;
            }
        }

        // Return maximum valid H-index
        return result;
    }
}