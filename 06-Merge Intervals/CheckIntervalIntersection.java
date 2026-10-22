```java
/*
 * Problem Name: Check if Any Two Intervals Intersect
 * Difficulty: Easy
 * Pattern: Sorting + Scanning
 *
 * ------------------------------------------------------------
 * APPROACH: SORT + CHECK ADJACENT INTERVALS
 * ------------------------------------------------------------
 *
 * The objective is to determine whether any two intervals
 * intersect.
 *
 * Instead of comparing every pair of intervals using O(n^2)
 * time, we can sort the intervals and scan adjacent pairs.
 *
 * ------------------------------------------------------------
 * 1. SORT THE INTERVALS
 * ------------------------------------------------------------
 *
 * Arrays.sort(intervals,
 *     (a, b) -> Integer.compare(a[0], b[0]));
 *
 * Sort by starting point in ascending order.
 *
 * After sorting, an interval cannot start before the
 * interval immediately preceding it.
 *
 * ------------------------------------------------------------
 * 2. COMPARE ADJACENT INTERVALS
 * ------------------------------------------------------------
 *
 * for (int i = 0; i < intervals.length - 1; i++)
 *
 * Compare intervals[i] with intervals[i + 1].
 *
 * curr = intervals[i]
 * next = intervals[i + 1]
 *
 * If curr[1] >= next[0], the intervals intersect.
 *
 * Example:
 *
 * curr = [1, 5]
 * next = [4, 7]
 *
 * Since 5 >= 4, the intervals overlap.
 *
 * Return true immediately because only one intersecting
 * pair is needed to satisfy the problem.
 *
 * ------------------------------------------------------------
 * 3. WHY CHECKING ADJACENT INTERVALS IS SUFFICIENT
 * ------------------------------------------------------------
 *
 * Suppose an interval intersects a later interval in the
 * sorted array.
 *
 * Since the later interval starts before the earlier
 * interval ends, the scan must encounter an adjacent pair
 * whose intervals intersect along the way.
 *
 * Therefore, checking adjacent intervals is sufficient
 * to detect whether any intersection exists.
 *
 * ------------------------------------------------------------
 * 4. RETURN FALSE IF NO OVERLAP EXISTS
 * ------------------------------------------------------------
 *
 * If the loop completes without finding an intersection,
 * no pair of intervals intersects.
 *
 * Return false.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Sort by starting point.
 *
 * 2. Compare each interval with its immediate successor.
 *
 * 3. curr[1] >= next[0] means intersection.
 *
 * 4. Return true immediately when an overlap is found.
 *
 * 5. If no overlap is found, return false.
 *
 * 6. An empty array or a single interval produces false.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n log n)
 *
 * Sorting takes O(n log n).
 * The scan takes O(n).
 *
 * Overall: O(n log n).
 *
 * Space Complexity:
 *
 * The scan uses O(1) auxiliary space, excluding the sorting
 * implementation's temporary space.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.Arrays;

class Solution {
    static boolean isIntersect(int[][] intervals) {

        // Sort intervals by their starting points.
        Arrays.sort(
                intervals,
                (a, b) -> Integer.compare(a[0], b[0])
        );

        // Compare each interval with the next interval.
        for (int i = 0; i < intervals.length - 1; i++) {

            int[] curr = intervals[i];
            int[] next = intervals[i + 1];

            // If the current interval reaches the next
            // interval's start, an intersection exists.
            if (curr[1] >= next[0]) {
                return true;
            }
        }

        // No intersecting pair was found.
        return false;
    }
}
```