```java
/*
 * Problem Name: Interval List Intersections
 * LeetCode Number: 986
 * Difficulty: Medium
 * Pattern: Two Pointers + Intervals
 *
 * ------------------------------------------------------------
 * APPROACH: TWO POINTERS
 * ------------------------------------------------------------
 *
 * We are given two sorted lists of non-overlapping intervals.
 *
 * The objective is to return every intersection between an
 * interval in firstList and an interval in secondList.
 *
 * A brute-force approach compares every interval in the first
 * list with every interval in the second list.
 *
 * That takes O(n * m) time.
 *
 * Since both lists are sorted, we can solve the problem in
 * O(n + m) time using two pointers.
 *
 * ------------------------------------------------------------
 * 1. INITIALIZE TWO POINTERS
 * ------------------------------------------------------------
 *
 * int i = 0;
 * int j = 0;
 *
 * i points to the current interval in firstList.
 * j points to the current interval in secondList.
 *
 * We continue while both pointers remain within their lists:
 *
 * while (i < firstList.length && j < secondList.length)
 *
 * Once either list has no remaining intervals, there can be
 * no more intersections to discover.
 *
 * ------------------------------------------------------------
 * 2. IDENTIFY THE CURRENT INTERVALS
 * ------------------------------------------------------------
 *
 * int[] firstarray = firstList[i];
 * int[] secondarray = secondList[j];
 *
 * Every interval contains:
 *
 * interval[0] = start
 * interval[1] = end
 *
 * We compare the current interval from each list.
 *
 * ------------------------------------------------------------
 * 3. CHECK WHETHER THE INTERVALS OVERLAP
 * ------------------------------------------------------------
 *
 * firstarray[1] >= secondarray[0]
 *     && secondarray[1] >= firstarray[0]
 *
 * The first condition checks whether the first interval
 * ends at or after the second interval starts.
 *
 * The second condition checks whether the second interval
 * ends at or after the first interval starts.
 *
 * Both conditions must be true for an intersection to exist.
 *
 * Because endpoints are inclusive, intervals that share
 * exactly one endpoint also intersect.
 *
 * An equivalent and often easier-to-remember method is:
 *
 * start = Math.max(firstarray[0], secondarray[0]);
 * end   = Math.min(firstarray[1], secondarray[1]);
 *
 * If start <= end, the intersection exists.
 *
 * ------------------------------------------------------------
 * 4. CALCULATE THE INTERSECTION
 * ------------------------------------------------------------
 *
 * int[] result = {
 *     Math.max(firstarray[0], secondarray[0]),
 *     Math.min(firstarray[1], secondarray[1])
 * };
 *
 * Why Math.max for the start?
 *
 * The intersection cannot begin before either interval
 * begins, so we choose the later starting point.
 *
 * Why Math.min for the end?
 *
 * The intersection cannot continue beyond either interval
 * ending, so we choose the earlier ending point.
 *
 * Example:
 *
 * firstarray  = [0, 5]
 * secondarray = [3, 8]
 *
 * Start = max(0, 3) = 3
 * End   = min(5, 8) = 5
 *
 * Intersection = [3, 5]
 *
 * Add every valid intersection to the result list.
 *
 * ------------------------------------------------------------
 * 5. MOVE THE POINTER THAT ENDS FIRST
 * ------------------------------------------------------------
 *
 * if (firstarray[1] < secondarray[1]) {
 *     i++;
 * } else {
 *     j++;
 * }
 *
 * This is the most important two-pointer decision.
 *
 * If the first interval ends earlier, it cannot intersect
 * any later interval in the second list.
 *
 * Therefore, advance i.
 *
 * Otherwise, advance j.
 *
 * If both intervals end at the same position, advancing
 * either pointer is sufficient. Advancing both is also safe
 * and avoids an unnecessary comparison.
 *
 * This guarantees that at least one pointer advances in
 * every iteration, giving us linear time complexity.
 *
 * ------------------------------------------------------------
 * KEY OBSERVATIONS
 * ------------------------------------------------------------
 *
 * 1. Both interval lists are already sorted.
 *
 * 2. Intersection start = maximum of the two starts.
 *
 * 3. Intersection end = minimum of the two ends.
 *
 * 4. An intersection exists when start <= end.
 *
 * 5. Advance the pointer whose interval ends first.
 *
 * 6. A shared endpoint produces a valid intersection.
 *
 * 7. Stop when either list has been fully processed.
 *
 * ------------------------------------------------------------
 * EDGE CASES
 * ------------------------------------------------------------
 *
 * 1. One list is empty:
 *    Return an empty 2D array.
 *
 * 2. No intersections:
 *    Return an empty 2D array.
 *
 * 3. One interval is completely inside another:
 *    [2, 4] and [1, 6] produce [2, 4].
 *
 * 4. Intervals share one endpoint:
 *    [1, 5] and [5, 8] produce [5, 5].
 *
 * 5. One list contains only one interval:
 *    The two-pointer loop still works normally.
 *
 * ------------------------------------------------------------
 * COMPLEXITY ANALYSIS
 * ------------------------------------------------------------
 *
 * Let n = firstList.length and m = secondList.length.
 *
 * Time Complexity: O(n + m)
 *
 * At least one pointer advances in every iteration.
 * Each pointer advances at most the length of its list.
 *
 * Space Complexity: O(n + m) for the result list in the
 * worst case, because there can be O(n + m) intersections.
 *
 * The algorithm uses O(1) auxiliary space excluding the
 * output list.
 *
 * ------------------------------------------------------------
 * JAVA IMPLEMENTATION
 * ------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] intervalIntersection(
            int[][] firstList,
            int[][] secondList) {

        int i = 0;
        int j = 0;

        // Store every valid intersection.
        List<int[]> list = new ArrayList<>();

        // If either list is empty, no intersections exist.
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[0][2];
        }

        // Compare intervals while both lists have elements.
        while (i < firstList.length && j < secondList.length) {

            int[] firstarray = firstList[i];
            int[] secondarray = secondList[j];

            // Check whether the current intervals overlap.
            if (firstarray[1] >= secondarray[0]
                    && secondarray[1] >= firstarray[0]) {

                // The intersection starts at the later start
                // and ends at the earlier end.
                int[] result = {
                        Math.max(firstarray[0], secondarray[0]),
                        Math.min(firstarray[1], secondarray[1])
                };

                list.add(result);
            }

            // Advance the interval that ends first.
            if (firstarray[1] < secondarray[1]) {
                i++;
            } else if (firstarray[1] > secondarray[1]) {
                j++;
            } else {
                // If both ends are equal, advance both pointers.
                i++;
                j++;
            }
        }

        // Convert the list of intersections to a 2D array.
        return list.toArray(new int[list.size()][2]);
    }
}
```