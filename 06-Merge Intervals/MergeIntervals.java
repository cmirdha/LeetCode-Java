```java
/*
 * Problem Name: Merge Intervals
 * LeetCode Number: 56
 * Difficulty: Medium
 * Pattern: Sorting + Scanning
 *
 * ------------------------------------------------------------
 * APPROACH: SORT + MERGE
 * ------------------------------------------------------------
 *
 * The objective is to merge all overlapping intervals and
 * return a collection of non-overlapping intervals.
 *
 * Example:
 *
 * Input:
 * [[1,3], [2,6], [8,10], [15,18]]
 *
 * Output:
 * [[1,6], [8,10], [15,18]]
 *
 * A brute-force solution may repeatedly compare intervals
 * and merge them, potentially requiring O(n^2) time or more
 * depending on the implementation.
 *
 * A simpler optimized approach is:
 *
 * 1. Sort intervals by their starting values.
 * 2. Maintain the previous merged interval.
 * 3. Compare each current interval with the previous one.
 * 4. Merge when they overlap; otherwise, save the previous
 *    interval and start a new one.
 *
 * ------------------------------------------------------------
 * 1. SORT THE INTERVALS
 * ------------------------------------------------------------
 *
 * Arrays.sort(intervals,
 *     (a, b) -> Integer.compare(a[0], b[0]));
 *
 * Each interval has two values:
 *
 * intervals[i][0] = start
 * intervals[i][1] = end
 *
 * Sorting by start time ensures that intervals are processed
 * from the earliest start to the latest start.
 *
 * Why is sorting important?
 *
 * After sorting, a future interval cannot start before the
 * current interval.
 *
 * Therefore, when the current interval does not overlap
 * the previous merged interval, it cannot overlap any
 * earlier interval that has already been completed.
 *
 * This allows us to process intervals in one linear scan.
 *
 * ------------------------------------------------------------
 * 2. INITIALIZE THE PREVIOUS INTERVAL
 * ------------------------------------------------------------
 *
 * int[] prev = intervals[0];
 *
 * The first interval becomes the initial interval to merge.
 *
 * We begin the loop at index 1 because index 0 is already
 * stored in prev.
 *
 * The variable prev always represents the latest interval
 * that has not yet been finalized in the result list.
 *
 * ------------------------------------------------------------
 * 3. CHECK FOR OVERLAPPING INTERVALS
 * ------------------------------------------------------------
 *
 * if (prev[1] >= curr[0])
 *
 * Compare:
 *
 * prev[1] = end of the previous merged interval
 * curr[0] = start of the current interval
 *
 * If prev[1] >= curr[0], the intervals overlap or touch
 * at a common endpoint.
 *
 * Example:
 *
 * prev = [1, 3]
 * curr = [2, 6]
 *
 * Since 3 >= 2, merge the intervals.
 *
 * The merged interval becomes [1, 6].
 *
 * Update:
 *
 * prev[1] = Math.max(prev[1], curr[1]);
 *
 * Why use Math.max?
 *
 * Consider:
 *
 * prev = [1, 10]
 * curr = [2, 5]
 *
 * The current interval is completely contained inside prev.
 *
 * The merged interval must remain [1, 10], not [1, 5].
 *
 * Math.max preserves the farthest ending point.
 *
 * IMPORTANT:
 *
 * We do not need to update prev[0], because sorting ensures
 * that prev starts no later than curr.
 *
 * ------------------------------------------------------------
 * 4. HANDLE NON-OVERLAPPING INTERVALS
 * ------------------------------------------------------------
 *
 * else {
 *     list.add(prev);
 *     prev = curr;
 * }
 *
 * If prev[1] < curr[0], there is a gap between the intervals.
 *
 * Example:
 *
 * prev = [1, 6]
 * curr = [8, 10]
 *
 * Since 6 < 8, these intervals cannot be merged.
 *
 * First:
 *
 * list.add(prev);
 *
 * The previous interval is complete and can be saved.
 *
 * Next:
 *
 * prev = curr;
 *
 * The current interval becomes the new interval that may
 * overlap with subsequent intervals.
 *
 * ------------------------------------------------------------
 * 5. ADD THE LAST INTERVAL
 * ------------------------------------------------------------
 *
 * list.add(prev);
 *
 * This statement must execute after the loop.
 *
 * The loop adds prev only when it encounters a non-overlapping
 * interval. If the input ends while prev is still active,
 * that interval would otherwise be missing from the result.
 *
 * Adding prev after the loop ensures the final merged
 * interval is always included.
 *
 * ------------------------------------------------------------
 * 6. CONVERT THE LIST TO A 2D ARRAY
 * ------------------------------------------------------------
 *
 * return list.toArray(new int[list.size()][2]);
 *
 * The ArrayList stores the merged intervals as int[] objects.
 *
 * LeetCode expects an int[][] return value.
 *
 * toArray(new int[list.size()][2]) creates an array with
 * the required dimensions and places the list's intervals
 * into it.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Sort by interval start before scanning.
 *
 * 2. Compare the previous merged end with the current start.
 *
 * 3. If prev[1] >= curr[0], merge and retain the maximum end.
 *
 * 4. Otherwise, save prev and set prev = curr.
 *
 * 5. Always add the final prev after the loop.
 *
 * 6. Sorting makes a single scan sufficient.
 *
 * 7. The original interval arrays are referenced directly.
 *    Updating prev[1] therefore also modifies the corresponding
 *    input interval. This is acceptable for this LeetCode
 *    problem because the input does not need to be preserved.
 *
 * ------------------------------------------------------------
 * EDGE CASES
 * ------------------------------------------------------------
 *
 * 1. One interval:
 *    [[1, 5]] -> [[1, 5]]
 *
 * 2. All intervals overlap:
 *    [[1, 4], [2, 5], [3, 6]] -> [[1, 6]]
 *
 * 3. No intervals overlap:
 *    [[1, 2], [4, 5], [7, 8]]
 *    -> [[1, 2], [4, 5], [7, 8]]
 *
 * 4. One interval contains another:
 *    [[1, 10], [2, 5]] -> [[1, 10]]
 *
 * 5. Touching endpoints:
 *    [[1, 4], [4, 7]] -> [[1, 7]]
 *
 * Note: LeetCode guarantees at least one interval.
 * For reusable code that may receive an empty array,
 * return new int[0][2] before accessing intervals[0].
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n log n)
 *
 * Sorting takes O(n log n).
 * The subsequent scan takes O(n).
 * Therefore, the total time complexity is O(n log n).
 *
 * Space Complexity:
 *
 * O(n) for the result list in the worst case, when no
 * intervals overlap.
 *
 * The sorting implementation may also use additional
 * temporary space. The overall auxiliary-space details
 * depend on the sorting implementation and whether the
 * output storage is counted.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {

        // Handle empty input for a reusable implementation.
        if (intervals == null || intervals.length == 0) {
            return new int[0][2];
        }

        // Sort intervals by their starting values.
        Arrays.sort(
                intervals,
                (a, b) -> Integer.compare(a[0], b[0])
        );

        // Store the merged intervals.
        List<int[]> list = new ArrayList<>();

        // The first interval is the initial merge candidate.
        int[] prev = intervals[0];

        // Process each remaining interval.
        for (int i = 1; i < intervals.length; i++) {

            int[] curr = intervals[i];

            // Check whether the intervals overlap or touch.
            if (prev[1] >= curr[0]) {

                // Merge by keeping the farthest ending point.
                prev[1] = Math.max(prev[1], curr[1]);

            } else {

                // No overlap: save the completed interval.
                list.add(prev);

                // Begin processing the current interval.
                prev = curr;
            }
        }

        // The last pending interval must also be included.
        list.add(prev);

        // Convert the list into the required 2D array.
        return list.toArray(new int[list.size()][2]);
    }
}
```