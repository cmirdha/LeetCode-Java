```java
/*
 * Problem Name: Insert Interval
 * LeetCode Number: 57
 * Difficulty: Medium
 * Pattern: Merge Intervals + Linear Scan
 *
 * ------------------------------------------------------------
 * APPROACH: THREE-PHASE LINEAR SCAN
 * ------------------------------------------------------------
 *
 * We are given intervals that are already sorted by their
 * starting values and do not overlap.
 *
 * We must insert newInterval and merge any overlapping
 * intervals while preserving sorted order.
 *
 * Instead of adding the new interval and sorting everything,
 * we can process the input in one pass.
 *
 * PHASE 1: Add intervals that end before newInterval starts.
 * PHASE 2: Merge all intervals that overlap newInterval.
 * PHASE 3: Add the merged interval and remaining intervals.
 *
 * ------------------------------------------------------------
 * 1. INITIALIZE THE RESULT
 * ------------------------------------------------------------
 *
 * List<int[]> result = new ArrayList<>();
 *
 * The result list stores the intervals in their final order.
 *
 * Since the input is already sorted, we can append intervals
 * directly without sorting them again.
 *
 * ------------------------------------------------------------
 * 2. ADD INTERVALS BEFORE THE NEW INTERVAL
 * ------------------------------------------------------------
 *
 * while (i < intervals.length
 *        && intervals[i][1] < newInterval[0])
 *
 * intervals[i][1] is the end of the current interval.
 * newInterval[0] is the start of the new interval.
 *
 * If the current interval ends before the new interval starts,
 * they cannot overlap.
 *
 * Example:
 *
 * intervals = [[1, 2], [5, 7]]
 * newInterval = [8, 10]
 *
 * [1, 2] ends before [8, 10] starts.
 * Therefore, [1, 2] can be added directly.
 *
 * ------------------------------------------------------------
 * 3. MERGE OVERLAPPING INTERVALS
 * ------------------------------------------------------------
 *
 * while (i < intervals.length
 *        && intervals[i][0] <= newInterval[1])
 *
 * An overlap exists when the current interval starts before
 * or exactly when newInterval ends.
 *
 * Update both boundaries:
 *
 * newInterval[0] = Math.min(
 *     newInterval[0], intervals[i][0]
 * );
 *
 * newInterval[1] = Math.max(
 *     newInterval[1], intervals[i][1]
 * );
 *
 * Math.min keeps the earliest start.
 * Math.max keeps the farthest end.
 *
 * Example:
 *
 * newInterval = [2, 5]
 * current      = [3, 8]
 *
 * The merged interval becomes [2, 8].
 *
 * We continue merging because a later interval may also
 * overlap the expanded newInterval.
 *
 * ------------------------------------------------------------
 * 4. ADD THE MERGED INTERVAL
 * ------------------------------------------------------------
 *
 * result.add(newInterval);
 *
 * After the merging loop, newInterval contains the combined
 * range of the new interval and every overlapping interval.
 *
 * Add it exactly once.
 *
 * ------------------------------------------------------------
 * 5. ADD ALL REMAINING INTERVALS
 * ------------------------------------------------------------
 *
 * while (i < intervals.length) {
 *     result.add(intervals[i]);
 *     i++;
 * }
 *
 * All remaining intervals start after the merged interval
 * ends, so they cannot overlap with it.
 *
 * They are already sorted and can be appended directly.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. The input intervals are already sorted.
 *
 * 2. Intervals ending before newInterval starts can be
 *    added without merging.
 *
 * 3. Overlapping intervals expand the boundaries of
 *    newInterval.
 *
 * 4. After merging finishes, all remaining intervals
 *    can be appended directly.
 *
 * 5. No sorting is required.
 *
 * 6. Every existing interval is processed at most once.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Time Complexity: O(n)
 *
 * Every existing interval is visited at most once.
 *
 * Space Complexity: O(n)
 *
 * The result may contain up to n + 1 intervals.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();

        // Start at the first existing interval.
        int i = 0;
        int n = intervals.length;

        // PHASE 1:
        // Add intervals that end before newInterval starts.
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // PHASE 2:
        // Merge every interval that overlaps newInterval.
        while (i < n && intervals[i][0] <= newInterval[1]) {

            // Keep the earliest starting boundary.
            newInterval[0] = Math.min(
                    newInterval[0],
                    intervals[i][0]
            );

            // Keep the farthest ending boundary.
            newInterval[1] = Math.max(
                    newInterval[1],
                    intervals[i][1]
            );

            i++;
        }

        // Add the final merged interval.
        result.add(newInterval);

        // PHASE 3:
        // Append the remaining non-overlapping intervals.
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // Convert the result list into a 2D array.
        return result.toArray(new int[result.size()][2]);
    }
}
```